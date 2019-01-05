package com.snowruin.web4j.framework.security.aspect;

import com.snowruin.web4j.framework.aspect.proxy.AspectProxy;
import com.snowruin.web4j.framework.security.annotation.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @ClassName AuthzAannotationAspect
 * @Description TODO 授权注解切面
 * @Author zxm
 * @Date 2018/12/28 10:35
 * @Version 1.0
 **/
public class AuthzAannotationAspect extends AspectProxy {

    private static final Class [] ANNOTATION_CLASS_ARRAY = {User.class};


    @Override
    protected void before(Class<?> targetClass, Method method, Object[] params) throws Throwable {
        Annotation annotation = getAnnotation(targetClass,method);
        if(Objects.nonNull(annotation)){
            Class<? extends Annotation> aClass = annotation.annotationType();
            if(annotation.equals(User.class)){
                handlerUser();
            }

        }
    }


    private Annotation getAnnotation(Class<?> targetClass,Method targetMethod){
      for (Class<? extends  Annotation> annotationClass : ANNOTATION_CLASS_ARRAY){
          if(targetMethod.isAnnotationPresent(annotationClass)){
              return targetMethod.getAnnotation(annotationClass);
          }
          if(targetClass.isAnnotationPresent(annotationClass)){
              return targetClass.getAnnotation(annotationClass);
          }
      }
      return null;
    }


    private void handlerUser(){
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principals = subject.getPrincipals();
        if(Objects.isNull(principals) || principals.isEmpty()){
            throw  new AuthorizationException("当前用户未登录");
        }
    }
}
