package com.leo.common.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.leo.common.R;
import com.leo.common.http.ExceptionHandle;
import com.leo.common.utils.ToastUtil;
import com.leo.common.utils.eventbus.Event;
import com.leo.common.utils.eventbus.EventBusUtil;
import com.leo.common.widget.sweetalert.SweetAlertDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;
/**
 * 
 * @author LeoWang
 * 
 * @Package com.leo.common.base
 * 
 * @Description fargment基类，简单页面继承此类
 * 
 * @Date 2019/5/6 11:09
 * 
 * @modify:
 */
public abstract class BaseFragment extends Fragment implements IBaseView{
    private SweetAlertDialog mProgressDialog;
    private Activity mActivity;
    private Unbinder mUnBinder;

    /** 是否是第一次加载*/
    private boolean isFirstEnter = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null){
            if (isRegisterEventBus()){
                EventBusUtil.register(this);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        if (null != inflater){
            view = inflater.inflate(getLayoutId(), container, false);
            mUnBinder = ButterKnife.bind(this, view);
            init();
            initListener();
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isFirstEnter){
            initData();
            isFirstEnter = false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // UnBinder防止内存泄露
        if (mUnBinder != null && mUnBinder != Unbinder.EMPTY){
            mUnBinder.unbind();
        }
        this.mUnBinder = null;

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }

        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
    }

    @Override
    public void showToast(String msg) {
        mActivity.runOnUiThread(() -> ToastUtil.showToast(getActivity(), msg));
    }

    @Override
    public void showLoading(boolean isCancel) {
        if (mProgressDialog == null){
            mProgressDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.PROGRESS_TYPE);
        }

        if (mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }

        mProgressDialog.setTitleText(getString(R.string.common_loading));
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
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

    /**
     * 初始化变量
     */
    public abstract void init();

    /**
     * 初始化数据，onCreate()中init()方法初始化变量完毕后，在onStart中进行数据初始化，联网请求
     */
    protected abstract void initData();

    /**
     * 布局id
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化监听，此方法在onCreate()中
     */
    public abstract void initListener();

    /**
     * 设置是否需要注册EventBus
     * @return
     */
    protected abstract boolean isRegisterEventBus();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(Event event){
        if (event != null){

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBusCome(Event event) {
        if (event != null) {
            receiveStickyEvent(event);
        }
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

    @Override
    public void handleThrowable(Throwable throwable) {
        if (throwable instanceof Exception){
            ExceptionHandle.ResponeThrowable e = ExceptionHandle.handleException(throwable);
            showToast(e.code + ":" + e.getMessage());
        }else {
            ExceptionHandle.ResponeThrowable e = new ExceptionHandle.ResponeThrowable(throwable, ExceptionHandle.ERROR.UNKNOWN);
            showToast(e.code + ":" + e.getMessage());
        }
    }
}
