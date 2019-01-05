package com.snowruin.web4j.framework.security;

import java.util.Set;

/**
 * @ClassName Web4jSecurity
 * @Description TODO web4j security 接口
 *
 * 可在应用中实现该接口，或者这web4j.properties 文件中配置如下提供sql 的配置项：
 *
 * web4j.framework.security.jdbc.authc_query 根据用户获取密码
 * web4j.framework.security.jdbc.roles_query 根据用户获取角色集合
 * web4j.framework.security.jdbc.permission_query 根据角色获取权限名集合
 *
 * @Author zxm
 * @Date 2018/12/24 15:20
 * @Version 1.0
 **/
public interface Web4jSecurity  {

    /**
     * 获取用户密码
     * @param username
     * @return
     */
    public String getPassword(String username);

    /**
     * 根据用户名获取角色名集合
     * @param username
     * @return
     */
    public Set<String> getRoleNamesByUsername(String username);


    /**
     * 根据角色名 获取权限名集合
     * @param roleName
     * @return
     */
    public Set<String> getPermissionNamesByRole(String roleName);
}
