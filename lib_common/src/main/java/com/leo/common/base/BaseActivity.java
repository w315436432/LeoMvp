package com.leo.common.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.leo.common.R;
import com.leo.common.app.ActManager;
import com.leo.common.impl.ToolbarListener;
import com.leo.common.utils.ScreenUtil;
import com.leo.common.utils.ToastUtil;
import com.leo.common.utils.eventbus.Event;
import com.leo.common.utils.eventbus.EventBusUtil;
import com.leo.common.widget.sweetalert.SweetAlertDialog;
import com.leo.common.widget.toolbar.NormalToolbar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
public abstract class BaseActivity extends AppCompatActivity implements IBaseView, ToolbarListener {
    private SweetAlertDialog mProgressDialog;
    public Activity mActivity;
    protected final String TAG = this.getClass().getSimpleName();
    protected ActManager actManager = ActManager.getActManager();
    private Unbinder mUnBinder;

    /** 是否是第一次进入*/
    private boolean isFirstEnter = true;

    /** 状态栏*/
    private View mStatusView;
    protected NormalToolbar mToolbar;
    /** 子类视图容器**/
    private FrameLayout mContainerFl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindowParameter();
        try {
            setContentView(R.layout.common_base_act);
            initBaseViews();
            if (getLayoutId() != 0){
                LayoutInflater inflater = LayoutInflater.from(this);
                View containerView = inflater.inflate(getLayoutId(), null);
                mUnBinder = ButterKnife.bind(this, containerView);
                mContainerFl.removeAllViews();
                mContainerFl.addView(containerView);
                setStatusBarColor(R.color.common_theme);
            }
        }catch (Exception e){
            if (e instanceof InflateException) throw e;
            e.printStackTrace();
        }
        mActivity = this;
        actManager.addActivity(mActivity);

        setupActivityComponent();

        if (isRegisterEventBus()){
            EventBusUtil.register(this);
        }

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
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
    }

    /**
     * 初始化toolbar，为了避免未知坑，此处不用黄油刀
     */
    private void initBaseViews(){
        mStatusView = findViewByID(R.id.common_status_bar);
        mToolbar = findViewByID(R.id.common_toolbar);
        mToolbar.setToolbarListener(this);
        mContainerFl = findViewByID(R.id.common_fl_container);
    }

    @Override
    public void showToast(String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast(BaseActivity.this, msg);
            }
        });
    }

    @Override
    public void showLoading(boolean isCancel) {
        if (!isFinishing()){
            if (mProgressDialog == null){
                mProgressDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.PROGRESS_TYPE);
            }

            if (mProgressDialog.isShowing()){
                mProgressDialog.dismiss();
            }

            mProgressDialog.setTitleText(getString(R.string.common_loading));
            mProgressDialog.setCancelable(isCancel);
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
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

    /**
     * 设置是否需要注册EventBus
     * @return
     */
    public abstract boolean isRegisterEventBus();

    /**
     * 初始化非黄油刀绑定的控件
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T findViewByID(int id){
        return (T) super.findViewById(id);

    }

    /**
     * 隐藏StatusBar
     */
    public void hideStatusBar(){
        mStatusView.setVisibility(View.GONE);
    }

    /**
     * 设置状态栏颜色
     *
     * @param color
     */
    public void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(LinearLayout
                    .LayoutParams.MATCH_PARENT, ScreenUtil.getStatusBarHeight(this));
            mStatusView.setBackgroundResource(color);
            mStatusView.setLayoutParams(lParams);
            mStatusView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置状态栏等与Window相关的参数
     */
    protected void initWindowParameter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0+
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4~5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                    localLayoutParams.flags);
        }
    }

    /**
     * 设置左右按钮文字
     * @param leftStr
     * @param rightStr
     */
    public void setToolbarText(String leftStr, String rightStr){
        mToolbar.setLeftText(leftStr);
        mToolbar.setRightText(rightStr);
    }

    /**
     * 设置标题文字
     * @param topicStr
     */
    public void setToolbarTopic(String topicStr){
        mToolbar.setTopic(topicStr);
    }

    public String getTitleTest(){
        return mToolbar.getTitleText();
    }

    @Override
    public void leftListener() {
        finish();
    }

    @Override
    public void rightListener() {
        Toast.makeText(this, "右侧按钮", Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(Event event){
        if (event != null){
            switch (event.getCode()){
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBusCome(Event event) {
    }

    /**
     * 接受到分发事件
     * @param event 事件
     */
    protected void receiveEvent(Event event){
    }

    /**
     * 接受到分发的粘性事件
     *
     * @param event 粘性事件
     */
    protected void receiveStickyEvent(Event event) {

    }
}
