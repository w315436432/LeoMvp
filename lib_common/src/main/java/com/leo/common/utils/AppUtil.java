package com.leo.common.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.leo.common.R;
import com.socks.library.KLog;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.List;


/**
 * 
 * @author wcs
 * 
 * @Package cn.com.drpeng.runman.app
 * 
 * @Description app常用工具
 * 
 * @Date 2018/5/3 11:01
 * 
 * @modify:
 */

public class AppUtil {

    private AppUtil(){
        throw new IllegalStateException("don`t need instantiate");
    }

    /**
     * 获取应用版本号，需要判空
     * @param context 当前上下文
     * @param packageName 包名
     * @return 应用版本号
     */
    public static String getVersionName(Context context,@NonNull String packageName) {
        String version = null;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(packageName, 0);
            version = info.versionName;
        } catch (Exception e) {
            version = "";
        }
        return version;
    }

    /**
     * dip转pix
     *
     * @param dpValue db值
     * @return px值
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = getResources(context).getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 获得资源
     * @param context 上下文
     * @return Resources
     */
    public static Resources getResources(Context context) {
        return context.getResources();
    }

    /**
     * 得到字符数组
     * @param context 上下文
     * @param id StringArray
     * @return 字符资源中数组
     */
    public static String[] getStringArray(Context context, int id) {
        return getResources(context).getStringArray(id);
    }

    /**
     * pix转dip
     * @param context 上下文
     * @param pix px值
     * @return dp值
     */
    public static int pix2dip(Context context, int pix) {
        final float densityDpi = getResources(context).getDisplayMetrics().density;
        return (int) (pix / densityDpi + 0.5f);
    }


    /**
     * 从 dimens 中获得尺寸
     * @param context 上下文
     * @param id dimen~s id
     * @return dimen值
     */
    public static int getDimens(Context context, int id) {
        return (int) getResources(context).getDimension(id);
    }

    /**
     * 从 dimens 中获得尺寸
     * @param context 上下文
     * @param dimenName dimenName
     * @return dimen
     */
    public static float getDimens(Context context, String dimenName) {
        return getResources(context).getDimension(getResources(context).getIdentifier(dimenName, "dimen", context.getPackageName()));
    }

    /**
     * 从String 中获得字符
     * @param context 上下文
     * @param stringID 字符资源id
     * @return 字符
     */
    public static String getString(Context context, int stringID) {
        return getResources(context).getString(stringID);
    }

    /**
     * 从String 中获得字符
     * @param context 上下文
     * @param strName 名称
     * @return 字符
     */

    public static String getString(Context context, String strName) {
        return getString(context, getResources(context).getIdentifier(strName, "string", context.getPackageName()));
    }

    /**
     * findview
     * @param context 上下文
     * @param view view
     * @param viewName 控件名
     * @param <T> 类型
     * @return 控件
     */
    public static <T extends View> T findViewByName(Context context, View view, String viewName) {
        int id = getResources(context).getIdentifier(viewName, "id", context.getPackageName());
        T v = (T) view.findViewById(id);
        return v;
    }

    /**
     * findview
     * @param activity
     * @param viewName
     * @param <T>
     * @return
     */
    public static <T extends View> T findViewByName(Context context, Activity activity, String viewName) {
        int id = getResources(context).getIdentifier(viewName, "id", context.getPackageName());
        T v = (T) activity.findViewById(id);
        return v;
    }

    /**
     * 根据 layout 名字获得 id
     *
     * @param layoutName
     * @return
     */
    public static int findLayout(Context context, String layoutName) {
        int id = getResources(context).getIdentifier(layoutName, "layout", context.getPackageName());
        return id;
    }

    /**
     * 填充view
     *
     * @param detailScreen
     * @return
     */
    public static View inflate(Context context, int detailScreen) {
        return View.inflate(context, detailScreen, null);
    }

    /**
     * 通过资源id获得drawable
     *
     * @param rID
     * @return
     */
    public static Drawable getDrawablebyResource(Context context, int rID) {
        return getResources(context).getDrawable(rID);
    }

    /**
     * 跳转界面 3
     *
     * @param activity
     * @param homeActivityClass
     */
    public static void startActivity(Activity activity, Class homeActivityClass) {
        Intent intent = new Intent(activity.getApplicationContext(), homeActivityClass);
        activity.startActivity(intent);
    }

    /**
     * 跳转界面 4
     *
     * @param
     */
    public static void startActivity(Activity activity, Intent intent) {
        activity.startActivity(intent);
    }

    /**
     * 获得屏幕的宽度
     *
     * @return
     */
    public static int getScreenWidth(Context context) {
        return getResources(context).getDisplayMetrics().widthPixels;
    }

    /**
     * 获得屏幕的高度
     *
     * @return
     */
    public static int getScreenHeidth(Context context) {
        return getResources(context).getDisplayMetrics().heightPixels;
    }


    /**
     * 获得颜色
     */
    public static int getColor(Context context, int rid) {
        return getResources(context).getColor(rid);
    }

    /**
     * 获得颜色
     */
    public static int getColor(Context context, String colorName) {
        return getColor(context, getResources(context).getIdentifier(colorName, "color", context.getPackageName()));
    }

    /**
     * 移除孩子
     *
     * @param view
     */
    public static void removeChild(View view) {
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) parent;
            group.removeView(view);
        }
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        return false;
    }


    /**
     * MD5
     *
     * @param string
     * @return
     * @throws Exception
     */
    public static String encodeToMD5(String string) {
        byte[] hash = new byte[0];
        try {
            hash = MessageDigest.getInstance("MD5").digest(
                    string.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }


    /**
     * 全屏,并且沉侵式状态栏
     *
     * @param activity
     */
    public static void statuInScreen(Activity activity) {
        WindowManager.LayoutParams attrs = activity.getWindow().getAttributes();
        attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        activity.getWindow().setAttributes(attrs);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }


    /**
     * 配置 RecyclerView
     *
     * @param recyclerView
     * @param layoutManager
     * @deprecated Use {@link #configRecyclerView(RecyclerView, RecyclerView.LayoutManager)} instead
     */
    @Deprecated
    public static void configRecycleView(final RecyclerView recyclerView
            , RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 配置 RecyclerView
     *
     * @param recyclerView
     * @param layoutManager
     */
    public static void configRecyclerView(final RecyclerView recyclerView
            , RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 字符串英文小写转大写
     * @param text
     * @return
     */
    public static String strToUpperCase(String text){
        String str = "";
        if (!TextUtils.isEmpty(text)){
            str = text.toUpperCase();
        }
        return str;
    }

    public static boolean isCameraCanUse() {
        boolean canUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
        } catch (Exception e) {
            canUse = false;
        }
        if (canUse) {
            if (mCamera != null)
                mCamera.release();
            mCamera = null;
        }
        return canUse;
    }

    /**
     * 拨打电话
     * @param activity
     * @param telNumber
     */
    public static void callPhone(Activity activity, String telNumber){
        //如果输入不为空创建打电话的Intent
        if(!TextUtils.isEmpty(telNumber) && telNumber.trim().length() != 0){
            Intent phoneIntent = new Intent("android.intent.action.CALL",
                    Uri.parse("tel:" + telNumber));
            try {
                //启动
                activity.startActivity(phoneIntent);
            } catch (Exception e) {
                ToastUtil.showToast(activity, activity.getResources().getString(R.string.common_toast_open_call_phone_op));
            }
        }else{
            //否则Toast提示一下
            ToastUtil.showToast(activity, activity.getString(R.string.common_error_phone_number));
        }
    }

    /**
     * 拨打电话
     * @param context
     * @param telNumber
     */
    public static void callPhone(Context context, String telNumber){
        //如果输入不为空创建打电话的Intent
        if(!TextUtils.isEmpty(telNumber) && telNumber.trim().length() != 0){
            Intent phoneIntent = new Intent("android.intent.action.CALL",
                    Uri.parse("tel:" + telNumber));
            try {
                //启动
                context.startActivity(phoneIntent);
            } catch (Exception e) {
                ToastUtil.showToast(context, context.getResources().getString(R.string.common_toast_open_call_phone_op));
            }
        }else{
            //否则Toast提示一下
            ToastUtil.showToast(context, context.getString(R.string.common_error_phone_number));
        }
    }

    /**
     * 当前包名与工作台是否一致
     * @param context
     * @return true，一致在前台；false，在后台
     */
    public static boolean isCurrentPkgNameEquelMy(Context context){
        if (!TextUtils.isEmpty(getCurrentPkgName(context))){
            if (getCurrentPkgName(context).equals(context.getPackageName())){
                return true;
            }
        }
        return false;
    }

    /**
     * 查询当前进程名
     *
     * @param context
     * @return
     */
    public static String getCurrentPkgName(Context context) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        String pkgName = null;
        if (Build.VERSION.SDK_INT >= 22) {
            ActivityManager.RunningAppProcessInfo currentInfo = null;
            Field field = null;
            int START_TASK_TO_FRONT = 2;
            try {
                field = ActivityManager.RunningAppProcessInfo.class
                        .getDeclaredField("processState");
            } catch (Exception e) {
                e.printStackTrace();
            }

            List<ActivityManager.RunningAppProcessInfo> appList = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo app : appList) {
                if (app.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    Integer state = null;
                    try {
                        state = field.getInt(app);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (state != null && state == START_TASK_TO_FRONT) {
                        currentInfo = app;
                        break;
                    }
                }
            }
            if (currentInfo != null) {
                pkgName = currentInfo.processName;
            }
        } else {
            @SuppressWarnings("deprecation")
            List<ActivityManager.RunningTaskInfo> runTaskInfos = am.getRunningTasks(1);
            // 拿到当前运行的任务栈
            ActivityManager.RunningTaskInfo runningTaskInfo = runTaskInfos
                    .get(0);
            // 拿到要运行的Activity的包名
            pkgName = runningTaskInfo.baseActivity.getPackageName();
        }

        return pkgName;
    }

    /**
     * 获得登记页面url(物料)
     * @param commonUrl		配置接口返回的Url
     * @param userAccount	客户账号
     * @param employeeNo	员工编号
     * @param employeeName	员工姓名
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getSuppliesRegister(String commonUrl, String userAccount, String employeeNo, String employeeName
            , String orderId, String order_type, String cityNo){
        String trueName = "";
        try {
            if (!TextUtils.isEmpty(employeeName)){
                trueName = URLEncoder.encode(employeeName, "UTF-8");
                trueName = URLEncoder.encode(trueName, "UTF-8");
            }else {
                trueName = "";
            }
        } catch (Exception e) {
            KLog.d("转码错误");
        }
        String url = "";
        // 通用地址
        url = commonUrl + "?UserID=" + userAccount + "&MemberNo=" + employeeNo
                + "&TrueName=" + trueName + "&OrderID=" + orderId + "&OrderType=" + order_type + "&CityNo=" + cityNo;
        KLog.d("urlEncode:" + url);
        return url;
    }

    /**
     * 获得通用工单工作表单url
     * @param commonUrl
     * @param employeeNo
     * @param employeeName
     * @param orderId
     * @return
     */
    public static String getCommonOrderUrl(String commonUrl, String employeeNo, String employeeName, String orderId
            , String location, String order_type){
        String trueName = "";
        String trueLocation = "";
        try {
            if (!TextUtils.isEmpty(employeeName)){
                trueName = URLEncoder.encode(employeeName, "UTF-8");
                trueName = URLEncoder.encode(trueName, "UTF-8");
            }else {
                trueName = "";
            }

            if (!TextUtils.isEmpty(location)){
                trueLocation = URLEncoder.encode(employeeName, "UTF-8");
                trueLocation = URLEncoder.encode(trueName, "UTF-8");
            }else {
                trueLocation = "";
            }
        } catch (Exception e) {
            KLog.d("转码错误");
        }
        String url = "";
        // 通用地址
        url = commonUrl + "?MemberNo=" + employeeNo
                + "&TrueName=" + trueName + "&OrderID=" + orderId + "&Location=" + trueLocation + "&OrderType=" + order_type;
        KLog.d("urlEncode:" + url);
        return url;
    }

    /**
     * 是否有网络连接
     * @return
     */
    public static boolean isNetConnected(Context context){
        NetworkInfo.State wifiState = null;
        NetworkInfo.State mobileState = null;
        boolean isConnected = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        if (wifiState != null && mobileState != null
                && NetworkInfo.State.CONNECTED != wifiState
                && NetworkInfo.State.CONNECTED == mobileState) {
            // 手机网络连接成功
            isConnected = true;
        } else if (wifiState != null && mobileState != null
                && NetworkInfo.State.CONNECTED != wifiState
                && NetworkInfo.State.CONNECTED != mobileState) {
            // 手机没有任何的网络
            isConnected = false;
        } else if (wifiState != null && NetworkInfo.State.CONNECTED == wifiState) {
            // 无线网络连接成功
            isConnected = true;
        }
        return isConnected;
    }

    public static boolean isSupportStepCountSensor(Context context) {
        // 获取传感器管理器的实例
        SensorManager sensorManager = (SensorManager) context
                .getSystemService(context.SENSOR_SERVICE);
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        Sensor detectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        return countSensor != null || detectorSensor != null;
    }

    /**
     * 实现文本复制功能
     * @param content
     */
    public static void copyText(String content, Context context)
    {
        ClipboardManager clipboardManager = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setPrimaryClip(ClipData.newPlainText(null, content));
        if (clipboardManager.hasPrimaryClip()){
            clipboardManager.getPrimaryClip().getItemAt(0).getText();
        }
    }

}
