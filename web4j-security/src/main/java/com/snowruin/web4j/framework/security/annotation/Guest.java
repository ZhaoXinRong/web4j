package com.snowruin.web4j.framework.security.annotation;

import java.lang.annotation.*;

/**
 * @ClassName Guest
 * @Description TODO
 * @Author zxm
 * @Date 2018/12/28 10:31
 * @Version 1.0
 **/

@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Guest {

}
