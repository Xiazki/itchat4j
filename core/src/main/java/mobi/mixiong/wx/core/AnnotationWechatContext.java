package mobi.mixiong.wx.core;

import mobi.mixiong.wx.factory.MessageProduce;
import mobi.mixiong.wx.factory.WechatMessage;

/**
 * Created by xiang on 2017/7/2.
 */
public class AnnotationWechatContext extends AbstractWechatContext{

    @Override
    protected void initProcessor() {

    }

    @Override
    public HandlerMap getHandlerMap() {
        return null;
    }

    @Override
    public MessageProduce getMessageProduce() {
        return null;
    }

}
