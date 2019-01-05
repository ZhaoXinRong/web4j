package com.snowruin.web4j.framework.security.realm;

import com.snowruin.web4j.framework.common.utils.CodecUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
 * @ClassName Md5CredentialsMatcher
 * @Description TODO 密码匹配器
 * @Author zxm
 * @Date 2018/12/27 17:53
 * @Version 1.0
 **/
public class Md5CredentialsMatcher implements CredentialsMatcher {

    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken token1 = ((UsernamePasswordToken) token);
        // 明文
        String password = String.valueOf(token1.getPassword());

        // 获取数据库中的密码，md5 密文
        String passwordCoded = (String)info.getCredentials();

        return CodecUtils.md5(password).equals(passwordCoded);
    }
}
