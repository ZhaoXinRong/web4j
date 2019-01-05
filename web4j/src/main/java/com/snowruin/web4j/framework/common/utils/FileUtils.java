package com.snowruin.web4j.framework.common.utils;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName FileUtils
 * @Description TODO 文件操作类
 * @Author zxm
 * @Date 2018/12/21 15:19
 * @Version 1.0
 **/
public class FileUtils {

    private FileUtils(){}


    /**
     * 获取真实文件名
     * @param fileName
     * @return
     */
    public static String getRealFileName(String fileName){
        return FilenameUtils.getName(fileName);
    }

    /**
     * 创建文件
     * @param filePath
     * @return
     * @throws IOException
     */
    public static File createFile(String filePath) throws IOException {
        File file = new File(filePath);
        File parentFile = file.getParentFile();
        if(!parentFile.exists()){
            org.apache.commons.io.FileUtils.forceMkdir(file);
        }
        return file;
    }

}
