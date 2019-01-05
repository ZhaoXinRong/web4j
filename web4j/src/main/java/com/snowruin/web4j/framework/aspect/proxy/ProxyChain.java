package com.snowruin.web4j.framework.aspect.proxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import lombok.Getter;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @ClassName ProxyChain
 * @Description TODO  代理链
 * @Author zxm
 * @Date 2018/12/15 15:57
 * @Version 1.0
 **/
@Getter
public class ProxyChain {

    private final Class<?> targetClass;
    private final Object targetObject;
    private final Method targetMethod;
    private final MethodProxy methodProxy;
    private final Object[] methodParams;

    private List<Proxy> proxyList = Lists.newArrayList();
    private int proxyIndex = 0;

    public ProxyChain(Object targetObject,Class<?> targetClass, Method targetMethod, MethodProxy methodProxy, Object[] methodParams, List<Proxy> proxyList) {
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.methodParams = methodParams;
        this.proxyList = proxyList;
    }


    public Object doProxyChain() throws Throwable {
        Object methodResult;
        if (proxyIndex < proxyList.size()) {
            methodResult = proxyList.get(proxyIndex++).doProxy(this);
        } else {
            methodResult = methodProxy.invokeSuper(targetObject, methodParams);
        }
        return methodResult;
    }
}