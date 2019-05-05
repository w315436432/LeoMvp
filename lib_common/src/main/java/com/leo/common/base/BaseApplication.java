package com.leo.common.base;

import android.app.Application;

import com.leo.common.impl.App;

public abstract class BaseApplication extends Application implements App {
    private final String TAG = "LeoWang";

    private boolean LOG_DEBUG = true;
    private boolean isDebugArouter = true;

    private static BaseApplication instance;
}
