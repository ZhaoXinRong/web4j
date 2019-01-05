package com.snowruin.web4j.framework.common.hepler;

import com.google.common.collect.Lists;
import com.snowruin.web4j.framework.common.bean.FormParam;
import com.snowruin.web4j.framework.common.bean.Param;
import com.snowruin.web4j.framework.common.constant.ConfigConstant;
import com.snowruin.web4j.framework.common.utils.CodecUtils;
import com.snowruin.web4j.framework.common.utils.StreamUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName RequestHelper
 * @Description TODO 请求 hepler
 * @Author zxm
 * @Date 2018/12/21 15:51
 * @Version 1.0
 **/
public class RequestHelper {

    private RequestHelper(){}

    /**
     * 创建请求参数对象
     * @param request
     * @return
     */
    public static Param createParam(HttpServletRequest request){
        List<FormParam> formParamList = Lists.newArrayList();
        formParamList.addAll(parseParameterNames(request));
        try {
            formParamList.addAll(parseInputStream(request));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Param(formParamList);
    }


    private static List<FormParam> parseParameterNames(HttpServletRequest request){
        List<FormParam> formParamList = Lists.newArrayList();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String fieldName = parameterNames.nextElement();
            String[] parameterValues = request.getParameterValues(fieldName);
            if(ArrayUtils.isNotEmpty(parameterValues)){
                Object fieldValue;
                if(parameterValues.length == 1){
                    fieldValue = parameterValues[0];
                }else{
                    StringBuilder stringBuilder = new StringBuilder("");
                    for (int i = 0; i < parameterValues.length; i++) {
                        stringBuilder.append(parameterValues[i]);
                        if(i != parameterValues.length -1){
                            stringBuilder.append(ConfigConstant.SEPARATOR);
                        }

                    }
                    fieldValue = stringBuilder.toString();
                }
                formParamList.add(new FormParam(fieldName,fieldValue));
            }
        }
        return formParamList;
    }


    private static List<FormParam> parseInputStream(HttpServletRequest request) throws IOException {
        List<FormParam> formParamList = Lists.newArrayList();
        String body = CodecUtils.decodeURL(StreamUtils.getString(request.getInputStream()));
        if(StringUtils.isNotEmpty(body)){
            String[] split = StringUtils.split(body, "&");
            if(ArrayUtils.isNotEmpty(split)){
                for (String str : split){
                    String[] split1 = StringUtils.split(str, "=");
                    if(ArrayUtils.isNotEmpty(split1) && split1.length == 2){
                        String fieldName = split1[0];
                        String fieldValue = split1[1];
                        formParamList.add(new FormParam(fieldName,fieldValue));
                    }

                }
            }
        }

        return formParamList;
    }

}
