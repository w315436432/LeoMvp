package com.leo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.leo.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupActivityComponent() {

    }

    @Override
    protected void init() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.button, R.id.button2})
    public void onViewClicked(View view){
        if (view.getId() == R.id.button){
            Toast.makeText(this, "我是第一个", Toast.LENGTH_SHORT).show();
        }else if(view.getId() == R.id.button2){
            Toast.makeText(this, "This is the second button", Toast.LENGTH_SHORT).show();
        }
    }
}
