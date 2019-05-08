package com.leo.home.ui.home;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.leo.common.app.arouter.RouterConstants;
import com.leo.common.base.BaseActivity;
import com.leo.home.R;

/**
 *
 * @author LeoWang
 *
 * @Package com.wcs.home.ui.home
 *
 * @Description 非MVP,首页
 *
 * @Date 2019/5/7 10:09
 *
 * @modify:
 */
@Route(path = RouterConstants.ACT_URL_HOME)
public class HomeAct extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.home_act_mainpage;
    }

    @Override
    protected void setupActivityComponent() {

    }

    @Override
    protected void init() {
        setToolbarText("返回", "确定");
        setToolbarTopic("首页");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public boolean isRegisterEventBus() {
        return false;
    }
}
