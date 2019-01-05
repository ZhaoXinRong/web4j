package com.snowruin.web4j.framework.common.hepler;

import com.google.common.collect.Lists;
import com.snowruin.web4j.framework.common.bean.FileParam;
import com.snowruin.web4j.framework.common.bean.FormParam;
import com.snowruin.web4j.framework.common.bean.Param;
import com.snowruin.web4j.framework.common.utils.FileUtils;
import com.snowruin.web4j.framework.common.utils.StreamUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName UploadHelper
 * @Description TODO 上传文件  helper
 * @Author zxm
 * @Date 2018/12/21 14:56
 * @Version 1.0
 **/
@Slf4j
public class UploadHelper {

    private static final String TEMP_DIR ="javax.servlet.context.tempdir";
    /**
     * 文件上传对象tempdir
     */
    private static  ServletFileUpload servletFileUpload;


    /**
     * 初始化
     * @param servletContext
     */
    public static void init(ServletContext servletContext){
        File tempDir = (File) servletContext.getAttribute(TEMP_DIR);

        servletFileUpload = new ServletFileUpload(new DiskFileItemFactory(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD,tempDir));

        int uploadLimit = ConfigHelper.getAppUploadLimit();

        if(uploadLimit > 0 ){
            servletFileUpload.setFileSizeMax(uploadLimit * 1024 * 1024);
        }
    }


    /**
     * 判断请求是否为  Multipart 类型
     * @param request
     * @return
     */
    public  static boolean isMultipart(HttpServletRequest request){
        return ServletFileUpload.isMultipartContent(request);
    }


    /**
     * 创建请求参数对象
     * @param request
     * @return
     * @throws FileUploadException
     * @throws IOException
     */
    public static Param createParam(HttpServletRequest request) throws FileUploadException, IOException {
        List<FormParam> formParamList = Lists.newArrayList();
        List<FileParam> fileParamList = Lists.newArrayList();

        Map<String, List<FileItem>> fileItemListMap = servletFileUpload.parseParameterMap(request);

        if(MapUtils.isNotEmpty(fileItemListMap)){
            for (Map.Entry<String,List<FileItem>> fileItemListEntry : fileItemListMap.entrySet()){
                String fieldName = fileItemListEntry.getKey();
                List<FileItem> fileItemList = fileItemListEntry.getValue();
                if(CollectionUtils.isNotEmpty(fileItemList)){
                    for (FileItem fileItem : fileItemList){

                        if(fileItem.isFormField()){

                            String fieldValue = fileItem.getString("utf-8");
                            formParamList.add(new FormParam(fieldName,fieldValue));
                        }else{
                            String fileName = FileUtils.getRealFileName(new String(fileItem.getName().getBytes(),"utf-8"));
                            if(StringUtils.isNotEmpty(fieldName)){
                                long size = fileItem.getSize();
                                String contentType = fileItem.getContentType();
                                InputStream inputStream = fileItem.getInputStream();
                                fileParamList.add(new FileParam(fileName,fieldName,size,contentType,inputStream));
                            }
                        }
                    }
                }

            }
        }
        return new Param(fileParamList,formParamList);
    }

    /**
     * 上传文件
     * @param basePath
     * @param fileParam
     */
    public static void uploadFile(String basePath,FileParam fileParam){
        if(Objects.nonNull(fileParam)){
            String fileName = fileParam.getFileName();
            String ext =  fileName.substring(fileName.lastIndexOf("."),fileName.length());
            String filePath = basePath + ((System.currentTimeMillis()) << 1000) + ext;
            log.info("filePath ==>> {}",filePath);
            try {
                FileUtils.createFile(filePath);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileParam.getInputStream());
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath));
                StreamUtils.copyStream(bufferedInputStream,bufferedOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 批量上传
     * @param basePath
     * @param fileParamList
     */
    public static void uploadFile(String basePath,List<FileParam> fileParamList){
        if(CollectionUtils.isNotEmpty(fileParamList)){
            for (FileParam fileParam : fileParamList){
                uploadFile(basePath,fileParam);
            }
        }
    }



}
