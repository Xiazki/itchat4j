package mobi.mixiong.wx.core;

import mobi.mixiong.wx.factory.MessageProduce;

/**
 * Created by xiang on 2017/7/1.
 */
public class WechatSyncMessageProcessor implements MessageProcessor {

    private MessageProduce messageProduce;
    private WechatContext wechatContext;

    public WechatSyncMessageProcessor(WechatContext wechatContext, MessageProduce messageProduce) {
        this.wechatContext = wechatContext;
        this.messageProduce = messageProduce;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onClose() {

    }

    @Override
    public void setWchatContext(WechatContext wchatContext) {

    }
}
