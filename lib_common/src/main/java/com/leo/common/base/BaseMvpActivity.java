package com.leo.common.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.leo.common.utils.ReflectUtil;

public abstract class BaseMvpActivity<P extends BasePresenter, M extends IBaseModel> extends BaseActivity{
    public P mPresenter;
    public M mModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = ReflectUtil.getT(this, 0);
        mModel = ReflectUtil.getT(this, 1);
        mPresenter.onAttach(mModel, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.onDetach();
            mPresenter.dispose();
        }
        mPresenter = null;
    }
}