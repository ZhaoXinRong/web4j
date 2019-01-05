package com.snowruin.web4j.framework.security.helper;

import com.snowruin.web4j.framework.security.exception.AuthenticationException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import java.util.Objects;

/**
 * @ClassName SecurityHelper
 * @Description TODO
 * @Author zxm
 * @Date 2018/12/26 15:25
 * @Version 1.0
 **/
public class SecurityHelper {

    private SecurityHelper(){}


    public static void login(String username,String password) throws AuthenticationException {
        Subject subject = SecurityUtils.getSubject();
        if(Objects.nonNull(subject)){
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            subject.login(usernamePasswordToken);
        }

    }

    public static void logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

    }




}
