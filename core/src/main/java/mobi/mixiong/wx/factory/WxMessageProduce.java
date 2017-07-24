package mobi.mixiong.wx.factory;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by xiang on 2017/7/5.
 */
public class WxMessageProduce implements MessageProduce {

    private BlockingDeque<WechatMessage> blockingDeque = new LinkedBlockingDeque<>();

    @Override
    public void addMsg(WechatMessage wechatMessage) {
        blockingDeque.push(wechatMessage);
    }

    @Override
    public WechatMessage getMsg() {
        return blockingDeque.poll();
    }
}
