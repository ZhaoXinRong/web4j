package com.snowruin.web4j.framework.common.utils;

import java.io.*;

/**
 * @ClassName StreamUtils
 * @Description TODO 流操作工具类
 * @Author zxm
 * @Date 2018/12/17 9:29
 * @Version 1.0
 **/
public class StreamUtils {

    public static  String getString(InputStream inputStream) throws  IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line ;

        while ((line = reader.readLine()) != null){
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }


    /**
     * 将输入流复制到输出流
     * @param inputStream
     * @param outputStream
     */
    public static void copyStream(InputStream inputStream, OutputStream outputStream)  {

        byte [] _byte = new byte [4 * 1024];
        int length ;
        try {
            while ((length = inputStream.read(_byte,0,_byte.length)) != -1){
                outputStream.write(_byte,0,length);
            }
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
