package com.ellecity06.quickjar.bus;

import com.ellecity06.quickjar.bus.support.IBusManager;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * EvenBus管理者，库中默认使用此事件管理
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/25 17:14
 * 把成千上万的生灵放在一起，把坏的去掉，笼子里就没有那么欢腾了。----霍布斯
 *
 */
@Singleton
public class EventBusManager implements IBusManager {

    @Inject
    BusConfig mBusConfig;

    @Inject
    public EventBusManager() {
    }

    //订阅事件
    @Override
    public void register(Object subscriber) {
        if (!EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().register(subscriber);
        }
    }

    //取消订阅
    @Override
    public void unregister(Object subscriber) {
        if (EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().unregister(subscriber);
        }
    }

    //发送事件
    @Override
    public void postEvent(Object event) {
        EventBus.getDefault().post(event);
    }

    //发送粘性事件
    @Override
    public void postStickyEvent(Object event) {
        EventBus.getDefault().postSticky(event);
    }

    //开启Index加速
    public void openIndex() {
        if (mBusConfig.isUseIndex() && mBusConfig.getIndex() != null) {
            EventBus.builder().addIndex(mBusConfig.getIndex()).installDefaultEventBus();
        }
    }

    //终止事件继续传递
    public void cancelDelivery(Object event) {
        EventBus.getDefault().cancelEventDelivery(event);
    }

    //获取保存起来的粘性事件
    public <T> T getStickyEvent(Class<T> classType) {
        return EventBus.getDefault().getStickyEvent(classType);
    }

    //删除保存中的粘性事件
    public void removeStickyEvent(Object event) {
        EventBus.getDefault().removeStickyEvent(event);
    }


}
