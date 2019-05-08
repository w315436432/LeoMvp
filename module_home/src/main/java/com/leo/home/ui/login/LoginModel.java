package com.leo.home.ui.login;

import com.leo.common.base.BaseApplication;
import com.leo.common.base.BaseResponse;
import com.leo.common.http.ApiService;
import com.leo.common.http.entity.request.WeatherReqEntity;
import com.leo.common.http.entity.response.WeatherResEntity;
import com.leo.home.di.component.DaggerHomeComponent;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LoginModel implements LoginContract.LoginModel{

    @Inject
    ApiService apiService;

    public LoginModel(){
        DaggerHomeComponent.builder()
                .appComponent(BaseApplication.getInstance().getAppComponent())
                .build()
                .inject(this);
    }

    @Override
    public Observable<BaseResponse<WeatherResEntity>> queryWeather(String location, String type) {
        WeatherReqEntity entity = new WeatherReqEntity();
        entity.setLocation(location);
        entity.setType(type);
        return apiService.queryWeather(entity);
    }
}
