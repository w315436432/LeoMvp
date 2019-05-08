package com.leo.home.di.component;

import com.leo.common.di.component.AppComponent;
import com.leo.common.di.scope.ActivityScope;
import com.leo.home.ui.login.LoginModel;

import dagger.Component;
/**
 *
 * @author LeoWang
 *
 * @Package com.wcs.home.di.component
 *
 * @Description 哪里需要注哪里，已集成AppComponent中提供的实例，如模块内需要其他的独有的，可单独写module
 *
 * @Date 2019/5/7 10:04
 *
 * @modify:
 */
@ActivityScope
@Component(dependencies = AppComponent.class)
public interface HomeComponent {

    void inject(LoginModel loginModel);

}
