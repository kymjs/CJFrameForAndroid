package com.lody.plugin.reflect;

/**
 * 反射发生错误时抛出异常
 */
public class ReflectException extends RuntimeException {

    private static final long serialVersionUID = -2243843843843438438L;

    public ReflectException(String message) {
        super(message);
    }

    public ReflectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReflectException() {
        super();
    }

    public ReflectException(Throwable cause) {
        super(cause);
    }
}
