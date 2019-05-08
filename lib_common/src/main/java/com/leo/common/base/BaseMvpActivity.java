package com.leo.common.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.leo.common.utils.ReflectUtil;
import com.socks.library.KLog;

/**
 * 
 * @author LeoWang
 * 
 * @Package com.leo.common.base
 * 
 * @Description MVP模式基类
 * 
 * @Date 2019/5/7 16:29
 * 
 * @modify:
 */
public abstract class BaseMvpActivity<P extends BasePresenter, M extends IBaseModel> extends BaseActivity{
    public P mPresenter;
    public M mModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = ReflectUtil.getT(this, 0);
        mModel = ReflectUtil.getT(this, 1);
        mPresenter.onAttach(mModel, this);
        getLifecycle().addObserver(mPresenter);
    }

    @Override
    protected void onDestroy() {
        KLog.d("onDestroy" + this.getClass().toString());
        super.onDestroy();
        mPresenter = null;
    }
}
