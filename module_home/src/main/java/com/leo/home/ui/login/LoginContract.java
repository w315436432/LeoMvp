package com.leo.home.ui.login;

import com.leo.common.base.BasePresenter;
import com.leo.common.base.BaseResponse;
import com.leo.common.base.IBaseModel;
import com.leo.common.base.IBaseView;
import com.leo.common.http.entity.response.WeatherResEntity;

import io.reactivex.Observable;

public interface LoginContract {

    interface LoginModel extends IBaseModel{
        /**
         * 查询地区天气
         * @param location 地区名 (北京,北京市)
         * @param type base:返回实况天气 all:返回预报天气
         * @return WeatherResEntity
         */
        Observable<BaseResponse<WeatherResEntity>> queryWeather(String location, String type);
    }

    interface LoginView extends IBaseView {
        /**
         * 查询成功
         * @param response 查询到的结果
         */
        void queryWeatherSucc(BaseResponse<WeatherResEntity> response);
    }

    abstract class LoginPresenter extends BasePresenter<LoginModel, LoginView>{
        /**
         * 页面发起查询天气
         * @param location 地区
         * @param type 查询信息的类别
         */
        public abstract void queryWeather(String location, String type);
    }
}
