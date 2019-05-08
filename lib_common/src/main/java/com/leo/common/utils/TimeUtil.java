package com.leo.common.utils;
/**
 *
 * @author LeoWang
 *
 * @Package com.leo.common.utils
 *
 * @Description 时间相关方法工具类
 *
 * @Date 2019/5/6 11:16
 *
 * @modify:
 */
public class TimeUtil {
    /**
     * 获得时间戳
     * @return 时间戳字符串
     */
    public static String getStringTimestamp(){
        return String.valueOf((int) (System.currentTimeMillis()/1000));
    }
}
