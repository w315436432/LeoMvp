package com.leo.home.ui.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.leo.common.app.arouter.RouterConstants;
import com.leo.common.app.arouter.RouterKey;
import com.leo.common.base.BaseMvpActivity;
import com.leo.common.base.BaseResponse;
import com.leo.common.http.entity.response.WeatherResEntity;
import com.leo.home.R;
import com.leo.home.R2;

import butterknife.BindView;
import butterknife.OnClick;
import cn.leo.click.SingleClick;

/**
 * @author LeoWang
 * @Package com.wcs.home.ui.login
 * @Description
 * @Date 2019/5/7 16:24
 * @modify:
 */
@Route(path = RouterConstants.ACT_URL_LOGIN)
public class LoginAct extends BaseMvpActivity<LoginPresenter, LoginModel> implements LoginContract.LoginView{

    @Autowired(name = RouterKey.HOME_TEXT_TAG)
    public String testText;

    @BindView(R2.id.edt_account)
    EditText edtAccount;
    @BindView(R2.id.edt_pwd)
    EditText edtPwd;
    @BindView(R2.id.btn_login)
    Button btnLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.home_act_login;
    }

    @Override
    protected void setupActivityComponent() {

    }

    @Override
    protected void init() {
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mPresenter.queryWeather("北京", "base");
    }

    @Override
    public boolean isRegisterEventBus() {
        return false;
    }

    @SingleClick
    @OnClick(R2.id.btn_login)
    public void onViewClicked(View view){
        if (view.getId() == R.id.btn_login){
            ARouter.getInstance().build(RouterConstants.ACT_URL_HOME)
                    .navigation();
            finish();
        }
    }

    @Override
    public void queryWeatherSucc(BaseResponse<WeatherResEntity> response) {
        showToast(response.getData().getWinddirection());
    }
}
