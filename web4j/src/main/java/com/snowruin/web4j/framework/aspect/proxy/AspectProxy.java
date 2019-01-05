package com.snowruin.web4j.framework.aspect.proxy;

import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 切面代理
 *
 * @author huangyong
 * @since 1.0.0
 */
public abstract class AspectProxy implements Proxy {

    private static final Logger logger = LoggerFactory.getLogger(AspectProxy.class);

    @Override
    public final Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;

        Class<?> cls = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();

        begin();
        try {
            if (intercept(cls, method, params)) {
                before(cls, method, params);
                result = proxyChain.doProxyChain();
                after(cls, method, params, result);
            } else {
                result = proxyChain.doProxyChain();
            }
        } catch (Exception e) {
            logger.error("proxy failure", e);
            error(cls, method, params, e);
            throw e;
        } finally {
            end();
        }

        return result;
    }

    protected void begin() {
    }

    public boolean intercept(Class<?> targetClass, Method method, Object[] params) throws Throwable {
        return true;
    }

    protected void before(Class<?> targetClass, Method method, Object[] params) throws Throwable {
    }

    protected void after(Class<?> targetClass, Method method, Object[] params, Object result) throws Throwable {
    }

    protected void error(Class<?> targetClass, Method method, Object[] params, Throwable e) {
    }

    protected void end() {
    }
}