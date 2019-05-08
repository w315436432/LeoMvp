package com.leo;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.leo.common.app.arouter.RouterConstants;
import com.leo.common.app.arouter.RouterKey;
import com.leo.common.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Route(path = RouterConstants.ACT_URL_LOADING)
public class LoadingAct extends BaseActivity {

    private Disposable disposable;

    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideToolbar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != disposable){
            disposable.dispose();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_loading;
    }

    @Override
    protected void setupActivityComponent() {

    }

    @Override
    protected void init() {
        disposable = Flowable.intervalRange(0, 3 + 1, 0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(aLong -> {
                    textView.setText(String.valueOf(aLong));
                })
                .doOnComplete(() ->{
                    ARouter.getInstance().build(RouterConstants.ACT_URL_LOGIN)
                            .withString(RouterKey.HOME_TEXT_TAG, "测试文本")
                            .navigation();
                    finish();
                }).subscribe();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public boolean isRegisterEventBus() {
        return false;
    }

}
