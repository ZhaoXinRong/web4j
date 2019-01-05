package com.snowruin.web4j.framework.common.hepler;

import com.snowruin.web4j.framework.common.utils.ClassUtils;

/**
 * @ClassName HelperLoader
 * @Description TODO
 * @Author zxm
 * @Date 2018/12/15 16:31
 * @Version 1.0
 **/
public final class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
            ClassHelper.class,
            BeanHelper.class,
            AopHelper.class,
            IocHelper.class,
            ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtils.loadClass(cls.getName());
        }
    }
}