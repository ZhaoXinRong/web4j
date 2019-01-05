package com.snowruin.web4j.framework.common.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Method;

/**
 * @ClassName Handler
 * @Description TODO action 信息
 * @Author zxm
 * @Date 2018/12/15 17:03
 * @Version 1.0
 **/
@AllArgsConstructor
@Getter
public class Handler {

    /**
     * controller
     */
    private Class<?> controllerClass;

    /**
     * action 方法
     */
    private Method actionMethod;


}
