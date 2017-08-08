package mobi.xiazki.wx.factory;

/**
 * Created by xiang on 2017/7/1.
 */
public interface MessageHandler {

    void handle(WechatMessage wechatMessage);

    Integer getType();

}
