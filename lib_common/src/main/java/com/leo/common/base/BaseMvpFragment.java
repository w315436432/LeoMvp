package com.leo.common.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.leo.common.utils.ReflectUtil;

/**
 * 
 * @author LeoWang
 * 
 * @Package com.leo.common.base
 * 
 * @Description MVP模式Fragment基类
 * 
 * @Date 2019/5/6 11:09
 * 
 * @modify:
 */
public abstract class BaseMvpFragment<P extends BasePresenter, M extends IBaseView> extends BaseFragment{
    //引用V层和P层
    public P mPresenter;
    public M mBaseModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mPresenter = ReflectUtil.getT(this, 0);
        mBaseModel = ReflectUtil.getT(this, 1);
        mPresenter.onAttach(mBaseModel, this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null){
            mPresenter.onDetach();
        }
        mPresenter = null;
    }
}
