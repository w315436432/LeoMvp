package com.leo.common.impl;

import com.leo.common.di.component.AppComponent;
/**
 *
 * @author LeoWang
 *
 * @Package com.leo.common.impl
 *
 * @Description 提供AppComponent
 *
 * @Date 2019/5/8 15:09
 *
 * @modify:
 */
public interface App {
    /**
     * 提供Component，便于注入
     * @return AppComponent
     */
    AppComponent getAppComponent();
}
