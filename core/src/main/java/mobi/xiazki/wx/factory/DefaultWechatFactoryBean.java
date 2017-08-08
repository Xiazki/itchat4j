package mobi.xiazki.wx.factory;

import mobi.xiazki.wx.core.WechatContext;
import mobi.xiazki.wx.util.enums.WechatStatus;

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
