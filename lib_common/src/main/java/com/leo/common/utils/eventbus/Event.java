package com.leo.common.utils.eventbus;
/**
 * 
 * @author wcs
 * 
 * @Package com.leo.common.utils.eventbus
 * 
 * @Description 用于消息传递封装的对象
 * 
 * @Date 2019/5/5 15:24
 * 
 * @modify:
 */
public class Event<T> {
    private int code;
    private T data;

    public Event(int code){
        this.code = code;
    }

    public Event(int code, T data){
        this.code = code;
        this.data = data;
    }

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
}
