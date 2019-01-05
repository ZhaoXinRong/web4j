package com.snowruin.web4j.framework.common.bean;

import com.google.common.collect.Maps;
import lombok.Getter;

/**
 * @ClassName View
 * @Description TODO 视图与模型
 * @Author zxm
 * @Date 2018/12/15 17:36
 * @Version 1.0
 **/
@Getter
public class View {

    /**
     * 视图路径
     */
    private String path;

    /**
     * 模型数据
     */
    private final java.util.Map<String,Object> model;

    public View(String path){
        this.path = path;
        this.model = Maps.newHashMap();
    }

    public View addModel(String key,Object value){
        this.model.put(key,value);
        return this;
    }
}
