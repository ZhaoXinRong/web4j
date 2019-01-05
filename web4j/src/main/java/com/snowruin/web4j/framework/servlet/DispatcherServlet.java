package com.snowruin.web4j.framework.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snowruin.web4j.framework.common.bean.Data;
import com.snowruin.web4j.framework.common.bean.Handler;
import com.snowruin.web4j.framework.common.bean.Param;
import com.snowruin.web4j.framework.common.bean.View;
import com.snowruin.web4j.framework.common.hepler.*;
import com.snowruin.web4j.framework.common.utils.JsonUtils;
import com.snowruin.web4j.framework.common.utils.ReflectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName DispatcherServlet
 * @Description TODO 请求分发器
 * @Author zxm
 * @Date 2018/12/14 16:00
 * @Version 1.0
 **/
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        HelperLoader.init();
        ServletContext servletContext = getServletConfig().getServletContext();
        UploadHelper.init(servletContext);
        registerServlet(servletContext);
    }

    private void registerServlet(ServletContext servletContext) {
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping("/index.jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");

        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping("/favicon.ico");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.init(request,response);
        try {
            String requestMethod = request.getMethod().toLowerCase();
            String requestPath = request.getRequestURI();

            if (requestPath.contains("favicon.ico")){
                return ;
            }

            // 获取action 处理器
            Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);

            if(Objects.nonNull(handler)){
                // 获取 controller 类 及 bean 实例
                Class<?> controllerClass = handler.getControllerClass();
                Object controllerBean = BeanHelper.getBean(controllerClass);

                Param param = (UploadHelper.isMultipart(request)) ?
                            UploadHelper.createParam(request):
                            RequestHelper.createParam(request);

                Object result = param.isNotEmpty() ?
                        ReflectionUtils.invokeMethod(controllerBean,handler.getActionMethod(),param) :
                        ReflectionUtils.invokeMethod(controllerBean,handler.getActionMethod());

                new HandlerResult(request,response,result).handlerResult();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ServletHelper.destroy();
        }
    }


    private final static class  HandlerResult{

        private final HttpServletRequest request;
        private final HttpServletResponse response;
        private final Object result;

        public HandlerResult(final HttpServletRequest _request,final HttpServletResponse _response,final Object _result){
            this.request = _request;
            this.response = _response;
            this.result = _result;
        }

        public void handlerResult() throws IOException, ServletException {
            if(result instanceof View){
                handlerView(response,((View)result), request) ;
            }else{
                handlerJsonResult(((Data)result),response);
            }
        }

        private void handlerView(HttpServletResponse response,View view,HttpServletRequest request) throws IOException, ServletException {
            String path = view.getPath();
            if(StringUtils.isNotEmpty(path)) {
                if (path.startsWith("/")) {
                    response.sendRedirect(request.getContextPath() + path);
                } else {
                    Map<String, Object> model = view.getModel();
                    for (Map.Entry<String, Object> entry : model.entrySet()) {
                        request.setAttribute(entry.getKey(), entry.getValue());
                    }
                    request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
                }
            }
        }

        private void handlerJsonResult(Data data,HttpServletResponse response) throws IOException {
            if(Objects.nonNull(data.getModel())){
                response.setContentType("application/json;charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JsonUtils.toJson(data.getModel()));
                writer.flush();
                writer.close();
            }
        }
    }
}
