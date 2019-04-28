package com.leo.common.base;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.leo.common.app.ActManager;

import butterknife.Unbinder;
import cn.pedant.SweetAlert.SweetAlertDialog;

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
    private Unbinder mUnbinder;

    /** 是否是第一次进入*/
    private boolean isFirstEnter = true;
}
