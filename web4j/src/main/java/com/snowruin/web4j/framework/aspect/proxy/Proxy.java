package com.snowruin.web4j.framework.aspect.proxy;


/**
 * @ClassName Action
 * @Description TODO  代理接口
 * @Author zxm
 * @Date 2018/12/15 15:57
 * @Version 1.0
 **/
public interface Proxy {

    /**
     * 执行链式代理
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}