package com.snowruin.web4j.framework.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Objects;

/**
 * @ClassName JsonUtils
 * @Description TODO
 * @Author zxm
 * @Date 2018/12/17 10:23
 * @Version 1.0
 **/
public class JsonUtils {
    private JsonUtils(){}
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static  String toJson(Object o){
        if(Objects.isNull(o)){
            return null;
        }

        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static  Object toObject(String jsonString,Class<?> clazz){

        if(!StringUtils.isNotEmpty(jsonString) && StringUtils.isNotBlank(jsonString)){
            return null;
        }
        try {
            return objectMapper.readValue(jsonString,clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
