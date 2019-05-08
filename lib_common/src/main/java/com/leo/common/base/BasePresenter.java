package com.leo.common.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.leo.common.impl.IPresenter;
import com.socks.library.KLog;

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
public abstract class BasePresenter<M, V> implements IPresenter {
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

    @Override
    public void onCreate(LifecycleOwner owner) {
        KLog.d("BasePresenter.onCreate" + this.getClass().toString());
    }

    @Override
    public void onStart(LifecycleOwner owner) {
        KLog.d("BasePresenter.onStart" + this.getClass().toString());
    }

    @Override
    public void onResume(LifecycleOwner owner) {
        KLog.d("BasePresenter.onResume" + this.getClass().toString());
    }

    @Override
    public void onPause(LifecycleOwner owner) {
        KLog.d("BasePresenter.onPause" + this.getClass().toString());
    }

    @Override
    public void onStop(LifecycleOwner owner) {
        KLog.d("BasePresenter.onStop" + this.getClass().toString());
    }

    @Override
    public void onDestroy(LifecycleOwner owner) {
        KLog.d("BasePresenter.onDestroy" + this.getClass().toString());
    }

    @Override
    public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {

    }
}
