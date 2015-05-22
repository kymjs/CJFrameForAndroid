package com.lody.plugin.exception;

/**
 * Created by lody on 2015/3/27.
 */
public class PluginNotExistException extends RuntimeException {

    public PluginNotExistException() {}

    public PluginNotExistException(String detailMessage) {
        super(detailMessage);
    }

    public PluginNotExistException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public PluginNotExistException(Throwable throwable) {
        super(throwable);
    }
}
