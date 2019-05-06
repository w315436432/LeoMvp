package com.leo.common.http;

import com.google.gson.JsonParseException;
import com.leo.common.R;
import com.leo.common.base.BaseApplication;
import com.leo.common.utils.AppUtil;

import org.json.JSONException;

import java.net.ConnectException;

import retrofit2.HttpException;

/**
 *
 * @author LeoWang
 *
 * @Package com.leo.common.http
 *
 * @Description 网络错误处理封装
 *
 * @Date 2019/5/6 11:01
 *
 * @modify:
 */
public class ExceptionHandle {

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ResponeThrowable handleException(Throwable e) {
        ResponeThrowable ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ResponeThrowable(e, ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                    ex.code = httpException.code();
                    ex.message = AppUtil.getString(BaseApplication.getInstance(), R.string.common_error_status_code_401);
                    break;
                case FORBIDDEN:
                    ex.code = httpException.code();
                    ex.message = AppUtil.getString(BaseApplication.getInstance(), R.string.common_error_status_code_403);
                    break;
                case NOT_FOUND:
                    ex.code = httpException.code();
                    ex.message = AppUtil.getString(BaseApplication.getInstance(), R.string.common_error_status_code_404);
                    break;
                case REQUEST_TIMEOUT:
                    ex.code = httpException.code();
                    ex.message = AppUtil.getString(BaseApplication.getInstance(), R.string.common_error_status_code_408);
                    break;
                case INTERNAL_SERVER_ERROR:
                    ex.code = httpException.code();
                    ex.message = AppUtil.getString(BaseApplication.getInstance(), R.string.common_error_status_code_500);
                    break;
                case BAD_GATEWAY:
                    ex.code = httpException.code();
                    ex.message = AppUtil.getString(BaseApplication.getInstance(), R.string.common_error_status_code_502);
                    break;
                case GATEWAY_TIMEOUT:
                    ex.code = httpException.code();
                    ex.message = AppUtil.getString(BaseApplication.getInstance(), R.string.common_error_status_code_504);
                    break;
                case SERVICE_UNAVAILABLE:
                    ex.code = httpException.code();
                    ex.message = AppUtil.getString(BaseApplication.getInstance(), R.string.common_error_status_code_503);
                    break;
                default:
                    ex.code = httpException.code();
                    ex.message = AppUtil.getString(BaseApplication.getInstance(), R.string.common_error_status_code_default);
                    break;
            }
            return ex;
        } else if (e instanceof ServerException) {
            ServerException resultException = (ServerException) e;
            ex = new ResponeThrowable(resultException, resultException.code);
            ex.message = resultException.message;
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
            /*|| e instanceof ParseException*/) {
            ex = new ResponeThrowable(e, ERROR.PARSE_ERROR);
            ex.message = AppUtil.getString(BaseApplication.getInstance(), R.string.common_error_status_parse_error);
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ResponeThrowable(e, ERROR.NETWORD_ERROR);
            ex.message = AppUtil.getString(BaseApplication.getInstance(), R.string.common_error_status_connect_error);
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ResponeThrowable(e, ERROR.SSL_ERROR);
            ex.message = AppUtil.getString(BaseApplication.getInstance(), R.string.common_error_status_ssl_error);
            return ex;
        } else {
            ex = new ResponeThrowable(e, ERROR.UNKNOWN);
            ex.message = AppUtil.getString(BaseApplication.getInstance(), R.string.common_error_status_unknown_error);
            return ex;
        }
    }


    /**
     * 约定异常
     */
    public class ERROR {
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        public static final int NETWORD_ERROR = 1002;
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = 1003;

        /**
         * 证书出错
         */
        public static final int SSL_ERROR = 1005;
    }

    public static class ResponeThrowable extends Exception {
        public int code;
        public String message;

        public ResponeThrowable(Throwable throwable, int code) {
            super(throwable);
            this.code = code;
        }
    }

    /**
     * ServerException发生后，将自动转换为ResponeThrowable 返回在onError(e)中
     */
    class ServerException extends RuntimeException {
        int code;
        String message;
    }
}
