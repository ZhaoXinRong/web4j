package com.snowruin.web4j.framework.common.annotation;

import java.lang.annotation.*;

/**
 * @ClassName Transaction
 * @Description TODO 事务
 * @Author zxm
 * @Date 2018/12/20 11:44
 * @Version 1.0
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Transaction {
}
