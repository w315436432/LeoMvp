package com.leo;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.leo.common.base.BaseApplication;

public class MainApplication extends BaseApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
