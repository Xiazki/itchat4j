package mobi.xiazki.wx.core;

import mobi.xiazki.wx.factory.*;
import mobi.xiazki.wx.util.enums.MessageType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiang on 2017/7/5.
 */
public class DefaultWechatContext extends AbstractWechatContext {

    private HandlerMap handlerMap;
    private MessageProduce messageProduce;

    public DefaultWechatContext(List<MessageHandler> handlers) {
        this.handlerMap = new HandlerMap();
        this.messageProduce = new WxMessageProduce();
        initHandlerMap(handlers);
    }

    private void initHandlerMap(List<MessageHandler> handlers) {
        handlers.stream().forEach(messageHandler -> {
            handlerMap.setMessageHandler(messageHandler.getType(), messageHandler);
        });
    }

    @Override
    public HandlerMap getHandlerMap() {
        return handlerMap;
    }

    @Override
    public MessageProduce getMessageProduce() {
        return this.messageProduce;
    }

    public static void main(String[] args) {
        WechatContext wechatContext = new DefaultWechatContext(new ArrayList<MessageHandler>() {
            {
                add(new MessageHandler() {
                    @Override
                    public void handle(WechatMessage wechatMessage) {
                        System.out.println("test");
                    }

                    @Override
                    public Integer getType() {
                        return MessageType.TEXT.getType();
                    }
                });
            }
        });
        Wechat wechat = wechatContext.getWechat();
        wechat.login();
    }
}
