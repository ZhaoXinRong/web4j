package com.snowruin.web4j.framework.aspect.transaction;

import com.snowruin.web4j.framework.aspect.proxy.Proxy;
import com.snowruin.web4j.framework.aspect.proxy.ProxyChain;
import com.snowruin.web4j.framework.common.annotation.Transaction;
import com.snowruin.web4j.framework.common.hepler.DatabaseHelper;
import com.snowruin.web4j.framework.common.lang.ThreadLocalCustom;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @ClassName transaction
 * @Description TODO 事务代理
 * @Author zxm
 * @Date 2018/12/20 11:58
 * @Version 1.0
 **/

@Slf4j
public class TransactionProxy implements Proxy {

    private static final ThreadLocalCustom<Boolean> FLAG_HODLER = new ThreadLocalCustom<Boolean>(){
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Boolean flag = FLAG_HODLER.get();
        Method targetMethod = proxyChain.getTargetMethod();
        Object o;
        if(!flag && targetMethod.isAnnotationPresent(Transaction.class)){
            FLAG_HODLER.set(true);
            try {
                DatabaseHelper.beginTransaction();
                log.info("=>=========>事务开始<============<=");
                o = proxyChain.doProxyChain();
                DatabaseHelper.commitTransaction();
                log.info("=>=========>事务提交<============<=");
            }catch (Exception e){
                DatabaseHelper.rollbackTransaction();
                log.info("=>=========>事务回滚<============<=");
                throw  e;
            }finally {
                FLAG_HODLER.remove();
            }
        }else{
            o = proxyChain.doProxyChain();
        }
        return o;
    }
}
