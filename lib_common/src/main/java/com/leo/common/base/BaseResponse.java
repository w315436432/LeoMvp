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

    /**
     * method : Com.Think.getWeatherData
     * time : 1548047960
     * status : {"code":1,"remind":"SUCCESS"}
     * data : {}
     */

    private String method;
    private int time;
    private StatusBean status;
    private T data;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static class StatusBean {
        /**
         * code : 1
         * remind : SUCCESS
         */

        private int code;
        private String remind;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getRemind() {
            return remind;
        }

        public void setRemind(String remind) {
            this.remind = remind;
        }
    }
}
