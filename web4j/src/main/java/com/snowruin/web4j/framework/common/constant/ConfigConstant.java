package com.snowruin.web4j.framework.common.constant;

/**
 * @ClassName ConfigConstant
 * @Description TODO 常量类
 * @Author zxm
 * @Date 2018/12/15 16:31
 * @Version 1.0
 **/
public interface ConfigConstant {

    String CONFIG_FILE = "web4j.properties";

    String JDBC_DRIVER = "web4j.framework.jdbc.driverClassName";
    String JDBC_URL = "web4j.framework.jdbc.url";
    String JDBC_USERNAME = "web4j.framework.jdbc.username";
    String JDBC_PASSWORD = "web4j.framework.jdbc.password";

    String APP_BASE_PACKAGE = "web4j.framework.app.base_package";
    String APP_JSP_PATH = "web4j.framework.app.jsp_path";
    String APP_ASSET_PATH = "web4j.framework.app.asset_path";

    String APP_UPALOD_LIMIT="web4j.framework.app.upload_limit";

    /**
     * 字符串分隔符
     */
    String SEPARATOR = String.valueOf((char) 29);
}
