package com.snowruin.web4j.framework.common.bean;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName FormParam
 * @Description TODO 表单参数
 * @Author zxm
 * @Date 2018/12/20 17:37
 * @Version 1.0
 **/
@AllArgsConstructor
@Getter
public class FormParam {

    private String fieldName;

    private Object fieldValue;
}
