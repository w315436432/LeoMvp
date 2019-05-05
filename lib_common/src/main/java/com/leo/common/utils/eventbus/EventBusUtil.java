package com.leo.common.utils.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * 
 * @author wcs
 * 
 * @Package com.leo.common.utils.eventbus
 * 
 * @Description EventBus使用工具类
 * 
 * @Date 2019/5/5 15:26
 * 
 * @modify:
 */
public class EventBusUtil {
    public static void register(Object subscriber) {
        if (!EventBus.getDefault().isRegistered(subscriber)){
            EventBus.getDefault().register(subscriber);
        }
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }

    public static void sendStickyEvent(Event event) {
        EventBus.getDefault().postSticky(event);
    }
}
