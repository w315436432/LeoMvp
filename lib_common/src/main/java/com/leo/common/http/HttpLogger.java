package com.leo.common.http;

import androidx.annotation.Nullable;

import com.socks.library.KLog;

import okhttp3.logging.HttpLoggingInterceptor;
/**
 *
 * @author LeoWang
 *
 * @Package com.leo.common.http
 *
 * @Description 日志处理，拦截打印
 *
 * @Date 2019/5/6 10:07
 *
 * @modify:
 */
public class HttpLogger implements HttpLoggingInterceptor.Logger {

    @Override
    public void log(@Nullable String message) {
        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
        try {
            if (null != message){
                if ((message.startsWith("{") && message.endsWith("}"))
                        || (message.startsWith("[") && message.endsWith("]"))){
                    KLog.json(message);
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }
}
