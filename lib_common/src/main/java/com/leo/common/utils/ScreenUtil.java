package com.leo.common.utils;

import android.content.Context;

import java.lang.reflect.Field;

/**
 *
 * @author wcs
 *
 * @Package com.leo.common.utils
 *
 * @Description 屏幕工具类
 *
 * @Date 2019/4/29 10:40
 *
 * @modify:
 */
public class ScreenUtil {

    /**
     * 获取手机状态栏高度
     * @param context 上下文
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int height = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = context.getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }
}
