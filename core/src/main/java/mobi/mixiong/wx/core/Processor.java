package mobi.mixiong.wx.core;

/**
 * Created by xiang on 2017/7/1.
 */
public interface Processor {

    void onRefresh();

    void onClose();

    void setWchatContext(WechatContext wchatContext);
//    void onInit();
//
//    void onStart();
//
//

}
