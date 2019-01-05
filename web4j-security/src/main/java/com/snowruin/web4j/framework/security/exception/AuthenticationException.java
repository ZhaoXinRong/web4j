package com.snowruin.web4j.framework.security.exception;

/**
 * @ClassName AuthenticationException
 * @Description TODO 认证异常
 * @Author zxm
 * @Date 2018/12/26 15:28
 * @Version 1.0
 **/
public class AuthenticationException extends Exception {

    public AuthenticationException() {
        super();
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationException(Throwable cause) {
        super(cause);
    }

}
