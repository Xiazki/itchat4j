package mobi.mixiong.wx.factory;

/**
 * Created by xiang on 2017/7/1.
 */
public interface MessageProduce {

    void addMsg(WechatMessage wechatMessage);

    WechatMessage getMsg();

}
