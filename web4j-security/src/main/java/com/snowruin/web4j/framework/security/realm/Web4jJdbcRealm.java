package com.snowruin.web4j.framework.security.realm;

import com.snowruin.web4j.framework.common.hepler.DatabaseHelper;
import com.snowruin.web4j.framework.security.SecurityConfig;
import org.apache.shiro.realm.jdbc.JdbcRealm;

/**
 * @ClassName Web4jJdbcRealm
 * @Description TODO  jdbc realm
 * @Author zxm
 * @Date 2018/12/27 17:48
 * @Version 1.0
 **/
public class Web4jJdbcRealm extends JdbcRealm {
     public Web4jJdbcRealm(){
         super.setDataSource(DatabaseHelper.getDataSource());
         super.setAuthenticationQuery(SecurityConfig.getJdbcAuthcQuery());
         super.setUserRolesQuery(SecurityConfig.getJdbcRolesQuery());
         super.setPermissionsQuery(SecurityConfig.getJdbcPermissionsQuery());
         super.setPermissionsLookupEnabled(true);
         // 使用MD5
         super.setCredentialsMatcher(new Md5CredentialsMatcher());
     }
}
