package com.snowruin.web4j.framework.security.tag;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.RoleTag;

import java.util.Arrays;
import java.util.Objects;

/**
 * @ClassName HasAllRolesTag
 * @Description TODO 判断当前用户是否拥有其中所有的角色(逗号分隔，表示“与” 的关系)
 * @Author zxm
 * @Date 2018/12/27 19:00
 * @Version 1.0
 **/
public class HasAllRolesTag extends RoleTag {

    private static  final String ROLE_NAMES_SPLIT = ",";

    @Override
    protected boolean showTagBody(String roleName) {
        boolean hasAllRole = false;
        Subject subject = getSubject();
        if(Objects.nonNull(subject)){
            if(subject.hasAllRoles(Arrays.asList(ROLE_NAMES_SPLIT))){
                hasAllRole = true;
            }
        }
        return hasAllRole;
    }
}
