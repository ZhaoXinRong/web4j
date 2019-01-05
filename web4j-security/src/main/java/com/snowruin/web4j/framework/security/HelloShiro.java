package com.snowruin.web4j.framework.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName HelloShiro
 * @Description TODO
 * @Author zxm
 * @Date 2018/12/22 10:17
 * @Version 1.0
 **/

@Slf4j
public class HelloShiro {

    public static void main(String[] args) {

        // 初始化 securityManager
    	Factory<SecurityManager> securityManagerFactory = new IniSecurityManagerFactory("classpath:security.ini");

    	SecurityManager instance = securityManagerFactory.getInstance();
    	
    	SecurityUtils.setSecurityManager(instance);
    	
    	
    	
    	//  获取当前用户
    	Subject subject = SecurityUtils.getSubject();
    	
    	
    	// 登陆
    	UsernamePasswordToken token = new UsernamePasswordToken("security", "888888");

    	
    	subject.login(token);
    	
    	
    	log.info("登陆成功！hello {}",subject.getPrincipal());
    	
    	// 注销
    	subject.logout();
    	
    	
    	
    	
    	
    	
    	
    	




    }

}
