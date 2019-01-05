package com.snowruin.web4j.framework.aspect.annotation;

import java.lang.annotation.*;

/**
 * @ClassName Aspect
 * @Description TODO
 * @Author zxm 切面注解
 * @Date 2018/12/18 16:53
 * @Version 1.0
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Aspect {

    /**
     * 注解
     * @return
     */
    Class<? extends Annotation> value();

}
