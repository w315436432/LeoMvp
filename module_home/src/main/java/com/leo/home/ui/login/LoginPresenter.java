package com.leo.home.ui.login;

import com.leo.common.base.BaseResponse;
import com.leo.common.http.entity.response.WeatherResEntity;
import com.socks.library.KLog;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends LoginContract.LoginPresenter {
    @Override
    public void queryWeather(String location, String type) {
        if (null == getView()){
            return;
        }
        getView().showLoading(false);

        Observable<BaseResponse<WeatherResEntity>> observable = mModel.queryWeather(location, type);
        Disposable disposable = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse<WeatherResEntity>>() {
                    @Override
                    public void accept(BaseResponse<WeatherResEntity> response) throws Exception {
                        getView().hideLoading();
                        if (response.getStatus().getCode() == 1) {
                            getView().queryWeatherSucc(response);
                        } else {
                            getView().showToast(response.getStatus().getRemind());
                        }
                        KLog.d("服务器返回状态码：" + response.getStatus().getCode());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().hideLoading();
                        getView().handleThrowable(throwable);
                        KLog.d(throwable.getMessage());
                    }
                });
        // 此处添加订阅，并在onDestory中clear，防止内存泄露
        addDisposable(disposable);
    }
}
