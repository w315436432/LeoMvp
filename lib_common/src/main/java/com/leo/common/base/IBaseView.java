package com.leo.common.base;

public interface IBaseView {
    /**
     * 提示
     * @param msg 文本
     */
    void showToast(String msg);

    /**
     * 显示加载框
     * @param isCancel 是否可以取消
     */
    void showLoading(boolean isCancel);

    /**
     * 隐藏加载框
     */
    void hideLoading();

    /**
     * 重新登录
     */
    void loginRepeat();

    /**
     * 处理网络错误
     * @param throwable {@link Throwable}
     */
    void handleThrowable(Throwable throwable);
}
