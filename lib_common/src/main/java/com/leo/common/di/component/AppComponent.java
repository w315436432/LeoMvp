package com.leo.common.di.component;

import com.google.gson.Gson;
import com.leo.common.app.sp.AppSP;
import com.leo.common.base.BaseActivity;
import com.leo.common.base.BaseApplication;
import com.leo.common.di.module.AppModule;
import com.leo.common.di.module.ClientModule;
import com.leo.common.http.ApiService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ClientModule.class})
public interface AppComponent {
    AppSP getAppSP();
    Gson getGson();
    ApiService getApiService();

    void inject(BaseApplication application);
    void inject(BaseActivity activity);
}
