package mobi.mixiong.wx.factory;

/**
 * Created by xiang on 2017/7/2.
 */
public interface Wechat {

    void login();

    void sendMessage(String msg, String user);

}
