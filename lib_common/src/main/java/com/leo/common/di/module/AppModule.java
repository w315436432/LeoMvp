package com.leo.common.di.module;

import android.app.Application;

import com.google.gson.Gson;
import com.leo.common.app.sp.AppSP;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 *
 * @author wcs
 *
 * @Package com.leo.common.di.module
 *
 * @Description 提供app常用的实例
 *
 * @Date 2019/5/5 16:50
 *
 * @modify:
 */
@Module
public class AppModule {
    public Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Singleton
    @Provides
    public Application getApplication(){
        return application;
    }

    @Singleton
    @Provides
    public AppSP providerAppSP(){
        AppSP appSP = new AppSP(application);
        return appSP;
    }

    @Singleton
    @Provides
    public Gson providerGson(){
        Gson gson = new Gson();
        return gson;
    }
}
