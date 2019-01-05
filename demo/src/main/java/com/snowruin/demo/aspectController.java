package com.snowruin.demo;

import com.snowruin.web4j.framework.aspect.annotation.Aspect;
import com.snowruin.web4j.framework.aspect.proxy.AspectProxy;
import com.snowruin.web4j.framework.common.annotation.Controller;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @ClassName Customer
 * @Description TODO controller  切面
 * @Author zxm
 * @Date 2018/12/14 16:00
 * @Version 1.0
 **/
@Aspect(Controller.class)
@Slf4j
public class aspectController  extends AspectProxy {

    private long a;

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        log.info("=>============== begin ============<=");
        log.info(String.format("目标类: %s", cls.getName()));
        log.info(String.format("调用方法: %s", method.getName()));
        a = System.currentTimeMillis();
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
        log.info(String.format("耗时: %dms", (System.currentTimeMillis() - a) ));
        log.info("=>============= end ===============<=");
    }
}
