package mobi.xiazki.wx.core;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import mobi.xiazki.wx.factory.MessageHandler;

import java.util.List;
import java.util.Map;

/**
 * Created by xiang on 2017/7/2.
 */
public class HandlerMap {

    private Map<Integer, List<MessageHandler>> handlerMap = Maps.newConcurrentMap();

    public List<MessageHandler> getMessageHandler(Integer type) {
        return handlerMap.get(type);
    }

    public void setMessageHandler(Integer type, MessageHandler messageHandler) {
        List<MessageHandler> handlers = getMessageHandler(type);
        if (handlers == null) {
            handlers = Lists.newArrayList();
            handlerMap.put(type, handlers);
        }
        handlers.add(messageHandler);
    }
}
