package mobi.mixiong.wx.factory;

import mobi.mixiong.wx.core.WechatContext;
import mobi.mixiong.wx.util.enums.WechatStatus;

/**
 * Created by xiang on 2017/7/1.
 */
public class DefaultWechatFactoryBean implements WechatFactoryBean {

    private WechatContext wechatContext;

    public DefaultWechatFactoryBean(WechatContext wechatContext) {
        this.wechatContext = wechatContext;
    }

    @Override
    public Wechat getWechat() {
        return new Wechat() {
            @Override
            public void login() {
                wechatContext.getStatusMap().put(WechatStatus.LOGINSTATUS.type(), true);
            }

            @Override
            public void sendMessage(String msg, String user) {

            }

        };
    }
}
