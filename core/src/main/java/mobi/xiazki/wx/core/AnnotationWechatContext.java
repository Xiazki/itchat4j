package mobi.xiazki.wx.core;

import mobi.xiazki.wx.factory.MessageProduce;

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
