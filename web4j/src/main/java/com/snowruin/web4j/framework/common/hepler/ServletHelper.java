package com.snowruin.web4j.framework.common.hepler;

import com.snowruin.web4j.framework.common.lang.ThreadLocalCustom;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName ServletHelper
 * @Description TODO
 * @Author zxm
 * @Date 2018/12/21 18:27
 * @Version 1.0
 **/

@Slf4j
public class ServletHelper {

    private ServletHelper( HttpServletRequest request,HttpServletResponse response){
        this.request = request;
        this.response = response;
    }


    private static final ThreadLocalCustom<ServletHelper> THREAD_LOCAL_CUSTOM = new ThreadLocalCustom<>();

    private HttpServletRequest request;

    private HttpServletResponse response;


    /**
     * 初始化
     * @param _request
     * @param _response
     */
    public static void init(HttpServletRequest _request,HttpServletResponse _response){
        THREAD_LOCAL_CUSTOM .set(new ServletHelper(_request,_response));
    }

    /**
     * 销毁
     */
    public static void destroy(){
        THREAD_LOCAL_CUSTOM.remove();
    }

    /**
     * 获取 response
     * @return
     */
    public static HttpServletResponse getResponse(){
        return THREAD_LOCAL_CUSTOM.get().response;
    }

    /**
     * 获取request
     * @return
     */
    public static HttpServletRequest getRequest(){
        return THREAD_LOCAL_CUSTOM.get().request;
    }

    /**
     * 获取session
     * @return
     */
    public static HttpSession getSession(){
        return getRequest().getSession();
    }

    /**
     * 设置 request 作用域的属性/值
     * @param attributeName
     * @param attributeValue
     */
    public static void  setRequestAttribute(String attributeName,Object attributeValue){
        getRequest().setAttribute(attributeName,attributeValue);
    }

    /**
     * 从 request 中获取值
     * @param attributeName
     * @return
     */
    public static Object getRequestAttribute(String attributeName){
        return getRequest().getAttribute(attributeName);
    }

    /**
     * 从request 中移除属性
     * @param attributeName
     */
    public static void removeRequestAttribute(String attributeName){
        getRequest().removeAttribute(attributeName);
    }


    /**
     * 重定向
     * @param path
     */
    public static void sendRedirect(String path){
        try {
            getResponse().sendRedirect(getRequest().getContextPath() + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将属性/ 值放入 session
     * @param attributeName
     * @param attributeValue
     */
    public static void setSessionAttribute(String attributeName,Object attributeValue){
        getSession().setAttribute(attributeName,attributeValue);
    }


    /**
     * 从 session 中获取属性/值
     * @param attributeName
     * @return
     */
    public static Object getSessionAttribute(String attributeName){
        return getSession().getAttribute(attributeName);
    }


    /**
     * 从session 中删除属性
     * @param attributeName
     */
    public static void remove(String attributeName){
        getSession().removeAttribute(attributeName);
    }

    /**
     * session 失效
     */
    public static void invalidateSession(){
        getSession().invalidate();
    }
}
