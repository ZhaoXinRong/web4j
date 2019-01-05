package com.snowruin.web4j.framework.security;

import com.snowruin.web4j.framework.common.hepler.ConfigHelper;
import com.snowruin.web4j.framework.common.utils.ReflectionUtils;

/**
 * @ClassName SecurityConfig
 * @Description TODO 读取配置文件中的属性
 * @Author zxm
 * @Date 2018/12/27 17:16
 * @Version 1.0
 **/
public final class SecurityConfig {

    private SecurityConfig(){}

    public static String getRealms() {
        return ConfigHelper.getString(SecurityConstant.REALMS);
    }

    public static Web4jSecurity getWeb4jSecurity() {
        String className = ConfigHelper.getString(SecurityConstant.SMART_SECURITY);
        return (Web4jSecurity) ReflectionUtils.newInstance(className);
    }

    public static String getJdbcAuthcQuery() {
        return ConfigHelper.getString(SecurityConstant.JDBC_AUTHC_QUERY);
    }

    public static String getJdbcRolesQuery() {
        return ConfigHelper.getString(SecurityConstant.JDBC_ROLES_QUERY);
    }

    public static String getJdbcPermissionsQuery() {
        return ConfigHelper.getString(SecurityConstant.JDBC_PERMISSIONS_QUERY);
    }

    public static boolean isCache() {
        return ConfigHelper.getBoolean(SecurityConstant.CACHE);
    }
}
