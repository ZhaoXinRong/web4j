package com.snowruin.web4j.framework.security.realm;

import com.google.common.collect.Sets;
import com.snowruin.web4j.framework.security.SecurityConstant;
import com.snowruin.web4j.framework.security.Web4jSecurity;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import java.util.Objects;
import java.util.Set;

/**
 * @ClassName Web4jCustomRealm
 * @Description TODO  自定义 realm
 * @Author zxm
 * @Date 2018/12/27 18:01
 * @Version 1.0
 **/
public class Web4jCustomRealm extends AuthorizingRealm {

    private final Web4jSecurity web4jSecurity;

    public Web4jCustomRealm(final Web4jSecurity web4jSecurity){
        this.web4jSecurity = web4jSecurity;
        super.setName(SecurityConstant.REALMS_CUSTOM);
        super.setCredentialsMatcher(new Md5CredentialsMatcher());
    }


    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if(Objects.isNull(principals)){
            throw new AuthenticationException("principals 为空");
        }

        // 获取已认证的用户名
        String username = (String)super.getAvailablePrincipal(principals);

        Set<String> roleNames = web4jSecurity.getRoleNamesByUsername(username);

        Set<String> primissionNames =  Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(roleNames)){
            for (String roleName : roleNames){
                Set<String> permissionNamesByRole = web4jSecurity.getPermissionNamesByRole(roleName);
                roleNames.addAll(permissionNamesByRole);
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        simpleAuthorizationInfo.setRoles(roleNames);

        simpleAuthorizationInfo.setStringPermissions(primissionNames);

        return simpleAuthorizationInfo;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if(Objects.isNull(token)){
            throw new AuthenticationException("token 为空");
        }

        // 表单中的 用户名
        String username = ((UsernamePasswordToken) token).getUsername();

        // 获取数据库中的密码
        String password = web4jSecurity.getPassword(username);

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo();

        simpleAuthenticationInfo.setPrincipals(new SimplePrincipalCollection(username,super.getName()));

        simpleAuthenticationInfo.setCredentials(password);

        return simpleAuthenticationInfo;
    }
}
