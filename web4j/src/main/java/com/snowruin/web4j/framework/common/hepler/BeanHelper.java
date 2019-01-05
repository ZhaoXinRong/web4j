package com.snowruin.web4j.framework.common.hepler;

import com.google.common.collect.Maps;
import com.snowruin.web4j.framework.common.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName BeanHelper
 * @Description TODO
 * @Author zxm
 * @Date 2018/12/15 16:31
 * @Version 1.0
 **/
@Slf4j
public final class BeanHelper {

    /**
     * bean 容器
     */
    private static final java.util.Map<Class<?> ,Object> BEAN_MAP =  new ConcurrentHashMap<>();

    private BeanHelper(){}

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> clazz : beanClassSet){
            Object o = ReflectionUtils.newInstance(clazz);
            BEAN_MAP.put(clazz,o);
        }
        log.info("加载了");
    }


    /**
     * 获取bean 映射
     * @return
     */
    public static  java.util.Map<Class<?>,Object> getBeanMap(){
        return BEAN_MAP;
    }

    /**
     * 获取bean 实例
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<?> clazz){
        if(!BEAN_MAP.containsKey(clazz)){
            throw  new RuntimeException("容器中不存在该class ： " +  clazz);
        }
        return (T) BEAN_MAP.get(clazz);
    }


    /**
     * 添加bean
     * @param clazz
     * @param object
     */
    public static void setBean(Class<?> clazz,Object object){
       BEAN_MAP.put(clazz,object);
    }

}
