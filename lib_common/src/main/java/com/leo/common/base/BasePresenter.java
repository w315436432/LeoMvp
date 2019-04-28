package com.leo.common.base;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
/**
 *
 * @author wcs
 *
 * @Package com.leo.common.base
 *
 * @Description
 *
 * @Date 2019/4/26 11:09
 *
 * @modify:
 */
public abstract class BasePresenter<M, V> {
    protected M mModel;
    protected WeakReference<V> mWeakRef;
    private CompositeDisposable disposable = new CompositeDisposable();

    protected void onAttach(M model, V view){
        mModel = model;
        mWeakRef = new WeakReference<>(view);
    }

    protected void onDetach(){
        if (null != mWeakRef){
            mWeakRef.clear();
            mWeakRef = null;
        }
    }

    protected boolean isViewAttached(){
        return null != mWeakRef && null != mWeakRef.get();
    }

    protected V getView(){
        return isViewAttached() ? mWeakRef.get() : null;
    }

    protected void addDisposable(Disposable dis){
        disposable.add(dis);
    }

    protected void dispose(){
        if (disposable != null){
            disposable.clear();
        }
    }
}
