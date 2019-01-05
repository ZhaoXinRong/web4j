package com.snowruin.web4j.framework.security.exception;

/**
 * @ClassName authorizeException
 * @Description TODO
 * @Author zxm
 * @Date 2018/12/26 15:35
 * @Version 1.0
 **/
public class authorizeException extends Exception {

    public authorizeException() {
        super();
    }

    public authorizeException(String message) {
        super(message);
    }

    public authorizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public authorizeException(Throwable cause) {
        super(cause);
    }
}
