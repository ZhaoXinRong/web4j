package com.snowruin.web4j.framework.aspect.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @ClassName ProxyManager
 * @Description TODO 代理管理器
 * @Author zxm
 * @Date 2018/12/18 17:24
 * @Version 1.0
 **/
public class ProxyManager {

    private ProxyManager(){}

    public static Object createProxy(final Class<?> targetClass, final List<Proxy> proxyList){
        return  Enhancer.create(targetClass,new MethodIntercpt(targetClass,proxyList));
    }


    private static class MethodIntercpt implements MethodInterceptor{
         private final Class<?> targetClass;
         private final List<Proxy> proxyList;

        public MethodIntercpt(Class<?> targetClass,List<Proxy> proxyList){
            this.targetClass = targetClass;
            this.proxyList = proxyList;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            return new ProxyChain(o,this.targetClass,method,methodProxy,objects,this.proxyList).doProxyChain();
        }
    }

}
