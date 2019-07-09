package com.leo.common.base;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.leo.common.di.component.AppComponent;
import com.leo.common.di.component.DaggerAppComponent;
import com.leo.common.di.module.AppModule;
import com.leo.common.impl.App;
import com.socks.library.KLog;
/**
 *
 * @author LeoWang
 *
 * @Package com.leo.common.base
 *
 * @Description 空壳app项目Application继承此类
 *
 * @Date 2019/5/6 14:22
 *
 * @modify:
 */
public abstract class BaseApplication extends Application implements App {
    final String TAG = "LeoWang";

    boolean LOG_DEBUG = true;
    boolean isDebugArouter = true;

    private static BaseApplication instance;

    private AppComponent appComponent;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        KLog.init(LOG_DEBUG, TAG);

        if (isDebugArouter) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }

    @Override
    public AppComponent getAppComponent() {
        if (null == appComponent){
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return appComponent;
    }
}
