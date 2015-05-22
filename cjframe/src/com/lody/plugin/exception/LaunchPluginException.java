package com.lody.plugin.exception;

/**
 * Created by lody on 2015/3/24.
 */
public class LaunchPluginException extends RuntimeException {
    public LaunchPluginException() {}

    public LaunchPluginException(String detailMessage) {
        super(detailMessage);
    }

    public LaunchPluginException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public LaunchPluginException(Throwable throwable) {
        super(throwable);
    }
}
