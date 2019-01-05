package com.snowruin.web4j.framework.security;

/**
 * 常量接口
 *
 * @author huangyong
 * @since 1.0.0
 */
public interface SecurityConstant {

    String REALMS = "web4j.framework.security.realms";
    String REALMS_JDBC = "jdbc";
    String REALMS_CUSTOM = "custom";

    String SMART_SECURITY = "web4j.framework.security.custom.class";

    String JDBC_AUTHC_QUERY = "web4j.framework.security.jdbc.authc_query";
    String JDBC_ROLES_QUERY = "web4j.framework.security.jdbc.roles_query";
    String JDBC_PERMISSIONS_QUERY = "web4j.framework.security.jdbc.permission_query";

    String CACHE = "web4j.framework.security.cache";
}
