package com.snowruin.web4j.framework.security.filter;

import com.google.common.collect.Sets;
import com.snowruin.web4j.framework.security.SecurityConfig;
import com.snowruin.web4j.framework.security.SecurityConstant;
import com.snowruin.web4j.framework.security.Web4jSecurity;
import com.snowruin.web4j.framework.security.realm.Web4jCustomRealm;
import com.snowruin.web4j.framework.security.realm.Web4jJdbcRealm;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.CachingSecurityManager;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;

import java.util.Set;

/**
 * @ClassName Web4jSecurityFilter
 * @Description TODO
 * @Author zxm
 * @Date 2018/12/27 17:16
 * @Version 1.0
 **/
public class Web4jSecurityFilter extends ShiroFilter {
    @Override
    public void init() throws Exception {
        WebSecurityManager securityManager = super.getSecurityManager();
        setRealms(securityManager);
        setCache(securityManager);
        super.init();
    }

    private void setRealms(WebSecurityManager securityManager){
        String realms = SecurityConfig.getRealms();
        if(StringUtils.isNotEmpty(realms)){
            // 根据都好进行拆分
            String[] realmsArray  = StringUtils.split(realms, ",");
            if(realmsArray.length > 0){
                // 使 realm 具有唯一性与顺序性
                Set<Realm> set =  Sets.newLinkedHashSet();
                for (String securityRealm : realmsArray){
                    if(realms.equalsIgnoreCase(SecurityConstant.REALMS_JDBC)){
                        // 添加基于  jdbc 的 realm ，需要配置 sql 语句
                        addJdbcRealm(set);
                    }else if (realms.equalsIgnoreCase(SecurityConstant.REALMS_CUSTOM)){
                        addCustomRealm(set);
                    }
                }

                RealmSecurityManager realmSecurityManager  = (RealmSecurityManager)securityManager;
                // 设置 realm
                realmSecurityManager.setRealms(set);
            }
        }
    }

    private void addJdbcRealm(Set<Realm> realmSet){
        Web4jJdbcRealm web4jJdbcRealm = new Web4jJdbcRealm();
        realmSet.add(web4jJdbcRealm);
    }

    private void addCustomRealm(Set<Realm> realmSet){

        Web4jSecurity smartSecurity = SecurityConfig.getWeb4jSecurity();

        Web4jCustomRealm web4jCustomRealm = new Web4jCustomRealm(smartSecurity);

        realmSet.add(web4jCustomRealm);

    }

    private void setCache(WebSecurityManager webSecurityManager){
        if(SecurityConfig.isCache()){
            CachingSecurityManager cachingSecurityManager =  (CachingSecurityManager)webSecurityManager;

            // 使用基于内存的 cachemanager
            CacheManager cacheManager = new MemoryConstrainedCacheManager();
            cachingSecurityManager.setCacheManager(cacheManager);
        }
    }
}
