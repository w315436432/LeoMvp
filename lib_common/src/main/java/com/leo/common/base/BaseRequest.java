package com.leo.common.base;

import com.leo.common.utils.TimeUtil;
/**
 *
 * @author LeoWang
 *
 * @Package com.leo.common.base
 *
 * @Description 根据自己需要封装相应的请求格式
 *
 * @Date 2019/5/6 11:15
 *
 * @modify:
 */
public class BaseRequest<T> {
    private String version = "1";
    private String timestamp;
    private T params;

    public BaseRequest(T params){
        timestamp = TimeUtil.getStringTimestamp();
        this.params = params;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }
}
