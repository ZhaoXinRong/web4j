package com.snowruin.web4j.framework.security.annotation;

import java.lang.annotation.*;

/**
 * @ClassName User
 * @Description TODO 判断当前用户是否已登陆(或已记住)
 * @Author zxm
 * @Date 2018/12/28 10:30
 * @Version 1.0
 **/

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface User {

}
