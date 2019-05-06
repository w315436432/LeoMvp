package com.leo.common.base;
/**
 * 
 * @author LeoWang
 * 
 * @Package com.leo.common.base
 * 
 * @Description 根据服务器返回格式自己封装
 * 
 * @Date 2019/5/6 11:17
 * 
 * @modify:
 */
public class BaseResponse<T> {
    private int code;
    private T data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
