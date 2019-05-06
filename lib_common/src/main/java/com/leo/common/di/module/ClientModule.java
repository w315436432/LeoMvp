package com.leo.common.di.module;

import com.leo.common.app.Constants;
import com.leo.common.http.ApiAddr;
import com.leo.common.http.ApiService;
import com.leo.common.http.HttpLogger;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author LeoWang
 *
 * @Package com.leo.common.di.module
 *
 * @Description 三方SDK实例
 *
 * @Date 2019/5/5 17:13
 *
 * @modify:
 */
@Module
public class ClientModule {
    public ClientModule(){

    }

    /**
     * 提供给providerRetrofit() Url
     * @return  BaseUrl
     */
    @Singleton
    @Provides
    public String providerBaseUrl(){
        return ApiAddr.BASE_URL;
    }

    /**
     * 提供给providerRetrofit() OkHttpClient
     * @return  OkHttpClient
     */
    @Singleton
    @Provides
    public OkHttpClient providerOkHttpClient(){
        OkHttpClient client = new OkHttpClient();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLogger());
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)      // 读取超时
                .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)   // 连接超时
                .addNetworkInterceptor(interceptor)
                .build();
        return client;
    }

    /**
     * 提供给providerApiService() Retrofit
     * @param okHttpClient  OkHttpClient
     * @param baseUrl   Api地址
     * @return Retrofit
     */
    @Singleton
    @Provides
    public Retrofit providerRetrofit(OkHttpClient okHttpClient, String baseUrl){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 提供ApiService
     * @param retrofit  网络请求实例
     * @return  ApiService
     */
    @Singleton
    @Provides
    public ApiService providerApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }
}
