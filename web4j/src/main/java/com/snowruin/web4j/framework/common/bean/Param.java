package com.snowruin.web4j.framework.common.bean;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.snowruin.web4j.framework.common.constant.ConfigConstant;
import com.snowruin.web4j.framework.common.utils.ConvertUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;

/**
 * @ClassName Param
 * @Description TODO
 * @Author zxm
 * @Date 2018/12/15 17:32
 * @Version 1.0
 **/

@Getter
public class Param {

    private List<FormParam> formParamList;

    private List<FileParam> fileParamList;

    public Param(List<FormParam> formParamList){
        this.formParamList = formParamList;
    }

    public Param(List<FileParam> fileParamList,List<FormParam> formParamList){
        this.fileParamList = fileParamList;
        this.formParamList = formParamList;
    }


    /**
     * 根据参数名称获取long类型参数值
     * @param name
     * @return
     */
    public long getLong(String name){
        return ConvertUtils.convertLong(getFieldMap().get(name));
    }


    /**
     * 判断 参数容器 是否为非空
     * @return
     */
    public boolean isNotEmpty(){
        return CollectionUtils.isNotEmpty(formParamList) || CollectionUtils.isNotEmpty(fileParamList);
    }

    /**
     * 获取请求参数映射
     * @return
     */
    public java.util.Map<String,Object> getFieldMap(){
        java.util.Map<String,Object> fieldMap =  Maps.newHashMap();
        if(CollectionUtils.isNotEmpty(formParamList)){
            for (FormParam formParam : formParamList){
                String fieldName = formParam.getFieldName();
                Object fieldValue = formParam.getFieldValue();

                if(fieldMap.containsKey(fieldName)){
                    fieldValue = fieldMap.get(fieldName) + ConfigConstant.SEPARATOR + fieldValue;
                }
                fieldMap.put(fieldName,fieldValue);
            }
        }
        return fieldMap;
    }

    /**
     * 获取上传文件映射
     * @return
     */
    public java.util.Map<String,List<FileParam>> getFileMap(){
        java.util.Map<String,List<FileParam>> fieldMap =  Maps.newHashMap();

        if(CollectionUtils.isNotEmpty(fileParamList)){
            for (FileParam fileParam : fileParamList){

                String fieldName = fileParam.getFieldName();

                List<FileParam> fileParamList;
                if(fieldMap.containsKey(fieldName)){
                    fileParamList = fieldMap.get(fieldName);
                }else{
                    fileParamList = Lists.newArrayList();
                }
                fileParamList.add(fileParam);
                fieldMap.put(fieldName,fileParamList);
            }
        }
        return fieldMap;
    }


    /**
     * 获取所有上传文件
     * @param fieldName
     * @return
     */
    public List<FileParam> getFileList(String fieldName){
        return getFileMap().get(fieldName);
    }

    /**
     * 获取唯一上传文件
     * @param fieldName
     * @return
     */
    public FileParam getFile(String fieldName){
        List<FileParam> fileList = getFileList(fieldName);
        if(CollectionUtils.isNotEmpty(fileList) && fileList.size() == 1){
            return fileList.get(0);
        }
        return null;
    }

    /**
     * 获取string 类型参数
     * @param name
     * @return
     */
    public String getString(String name){
        return ConvertUtils.convertString(getFieldMap().get(name));
    }

    /**
     * 获取double 类型参数
     * @param name
     * @return
     */
    public double getDouble(String name){
        return ConvertUtils.convertDouble(getFieldMap().get(name));
    }

    /**
     * 获取int 类型参数
     * @param name
     * @return
     */
    public int getInt(String name){
        return ConvertUtils.convertInt(getFieldMap().get(name));
    }


    /**
     * 获取boolean 类型参数
     * @param name
     * @return
     */
    public boolean getBoolean(String name){
        return ConvertUtils.convertBoolean(getFieldMap().get(name));
    }




}
