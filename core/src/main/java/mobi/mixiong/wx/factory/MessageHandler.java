package mobi.mixiong.wx.factory;

import java.lang.reflect.Type;

/**
 * Created by xiang on 2017/7/1.
 */
public interface MessageHandler {

    void handle(WechatMessage wechatMessage);

    Integer getType();

}
