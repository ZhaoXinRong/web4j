package com.snowruin.web4j.framework.common.annotation;

import java.lang.annotation.*;

/**
 * @ClassName Action
 * @Description TODO  方法注解
 * @Author zxm
 * @Date 2018/12/15 15:57
 * @Version 1.0
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {

    /**
     * 请求类型与路径
     */
    String value();
}
