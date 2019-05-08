package com.leo.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;
/**
 * 
 * @author wcs
 * 
 * @Package com.leo.common.utils
 * 
 * @Description 吐司工具
 * 
 * @Date 2019/5/5 15:35
 * 
 * @modify:
 */
public class ToastUtil {
    private static Toast toast;
    
    @SuppressLint("ShowToast")
    public static void showToast(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }
}
