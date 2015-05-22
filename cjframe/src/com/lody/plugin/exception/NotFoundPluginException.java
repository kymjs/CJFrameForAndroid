package com.lody.plugin.exception;

import android.content.res.Resources;

/**
 * Created by lody on 2015/3/27.
 */
public class NotFoundPluginException extends Resources.NotFoundException {

    public NotFoundPluginException(String pluginPath) {
        super("Not found Plugin on :" + pluginPath);
    }
}
