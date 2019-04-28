package com.leo.common.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.leo.common.R;
import com.leo.common.app.ActManager;
import com.leo.common.widget.sweetalert.SweetAlertDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 
 * @author wcs
 * 
 * @Package com.leo.common.base
 * 
 * @Description 非MVP模式Activity基类，页面交互简单继承此类
 * 
 * @Date 2019/4/26 16:22
 * 
 * @modify:
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView{
    private SweetAlertDialog mProgressDialog;
    public Activity mActivity;
    protected final String TAG = this.getClass().getSimpleName();
    protected ActManager actManager = ActManager.getActManager();
    private Unbinder mUnBinder;

    /** 是否是第一次进入*/
    private boolean isFirstEnter = true;

    private Toolbar mToolbar;
    private TextView mLeftTv;
    private TextView mTopicTv;
    private TextView mRightTv;
    private FrameLayout mContainerFl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.common_base_act);
            initToolbar();
            if (getLayoutId() != 0){
                LayoutInflater inflater = LayoutInflater.from(this);
                View containerView = inflater.inflate(getLayoutId(), null);
                mUnBinder = ButterKnife.bind(this, containerView);
            }
        }catch (Exception e){
            if (e instanceof InflateException) throw e;
            e.printStackTrace();
        }
        mActivity = this;
        actManager.addActivity(mActivity);

        setupActivityComponent();

        init();
        initListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isFirstEnter){
            initData();
            isFirstEnter = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        actManager.removeActivity(mActivity);
        if (mUnBinder != null && mUnBinder != Unbinder.EMPTY)
            mUnBinder.unbind();
        this.mUnBinder = null;

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    /**
     * 初始化toolbar，为了避免未知坑，此处不用黄油刀
     */
    private void initToolbar(){
        mToolbar = (Toolbar) findViewById(R.id.common_toolbar);
        mLeftTv = (TextView) findViewById(R.id.common_tv_left);
        mRightTv = (TextView) findViewById(R.id.common_tv_right);
        mTopicTv = (TextView) findViewById(R.id.common_tv_topic);
        mContainerFl = (FrameLayout) findViewById(R.id.common_fl_container);
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

    /**
     * 布局id
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 提供单例给实现类
     */
    protected abstract void setupActivityComponent();

    /**
     * 初始化变量及页面，此方法在onCreate()中
     */
    protected abstract void init();

    /**
     * 初始化监听，此方法在onCreate()中
     */
    public abstract void initListener();

    /**
     * 初始化数据，onCreate()中init()方法初始化变量完毕后，在onStart中进行数据初始化，联网请求
     */
    public abstract void initData();
}
