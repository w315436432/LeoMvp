package com.leo;

import android.os.Bundle;

import com.leo.common.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showLoading(boolean isCancel) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void loginRepeat() {

    }

    @Override
    public void handleThrowable(Throwable throwable) {

    }
}
