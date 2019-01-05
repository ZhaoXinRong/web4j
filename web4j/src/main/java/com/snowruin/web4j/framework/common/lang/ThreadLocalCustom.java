package com.snowruin.web4j.framework.common.lang;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName ThreadLocalCustom
 * @Description TODO 自定义的 threadLocal
 * @Author zxm
 * @Date 2018/12/20 10:35
 * @Version 1.0
 **/
public class ThreadLocalCustom<T> {

   private   Map<Thread,T> container = Collections.synchronizedMap(Maps.<Thread, T>newHashMap());

   public void set(T value){
       container.put(Thread.currentThread(),value);
   }

   public T get(){
       Thread thread = Thread.currentThread();
       T t = container.get(thread);
       if(Objects.isNull(t) && !container.containsKey(thread)){
           t = initialValue();
       }
       return t;
   }


   protected T initialValue(){
       return null;
   }

   public void remove(){
       container.remove(Thread.currentThread());
   }
}
