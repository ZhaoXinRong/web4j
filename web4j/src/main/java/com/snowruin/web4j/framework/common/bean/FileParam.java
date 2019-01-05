package com.snowruin.web4j.framework.common.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.InputStream;

/**
 * @ClassName FileParam
 * @Description TODO 文件上传参数
 * @Author zxm
 * @Date 2018/12/20 17:34
 * @Version 1.0
 **/
@AllArgsConstructor
@Getter
public class FileParam {

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 表单字段名称
     */
    private String fieldName;

    /**
     * 文件大小
     */
    private long fileSize;

    /**
     * 文件类型
     */
    private String contentType;

    private InputStream inputStream;
}
