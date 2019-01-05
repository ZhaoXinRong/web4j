package com.snowruin.web4j.framework.security.plugin;

import com.snowruin.web4j.framework.security.filter.Web4jSecurityFilter;
import org.apache.shiro.web.env.EnvironmentLoaderListener;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * @ClassName Web4jSecurityPlugin
 * @Description TODO
 * @Author zxm
 * @Date 2018/12/27 17:04
 * @Version 1.0
 **/
public class Web4jSecurityPlugin implements ServletContainerInitializer {

    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {

        // 设置初始化参数
        ctx.setInitParameter("shiroConfigLocations","classpath:web4j-security.ini");

        // 注册 linstener
        ctx.addListener(EnvironmentLoaderListener.class);

        // 注册 filter
        FilterRegistration.Dynamic web4jSecurityFilter = ctx.addFilter("Web4jSecurityFilter", Web4jSecurityFilter.class);

        web4jSecurityFilter.addMappingForUrlPatterns(null,false,"/*");
    }
}
