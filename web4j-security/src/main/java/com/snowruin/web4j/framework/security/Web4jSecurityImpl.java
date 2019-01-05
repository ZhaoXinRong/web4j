package com.snowruin.web4j.framework.security;

import com.snowruin.web4j.framework.common.hepler.DatabaseHelper;

import java.util.Set;

/**
 * @ClassName Web4jSecurityImpl
 * @Description TODO 安全控制实现类
 * @Author zxm
 * @Date 2018/12/24 15:30
 * @Version 1.0
 **/
public class Web4jSecurityImpl implements  Web4jSecurity {
    public String getPassword(String username) {
        String sql ="select password from user where username = ? ";
        return DatabaseHelper.query(sql,username);
    }

    public Set<String> getRoleNamesByUsername(String username) {
        String sql = "select r.role_name from user u,user_role ur,role r where u.id = ur.user_id and r.id = ur.role_id and u.username = ?";
        return DatabaseHelper.querySet(sql,username);
    }

    public Set<String> getPermissionNamesByRole(String roleName) {
        String sql = "select p.permission_name from role r,role_permission rp,permission p where r.id = rp.role_id and p.id = rp.permission_id and r.role_name = ? ";
        return DatabaseHelper.querySet(sql,roleName);
    }
}
