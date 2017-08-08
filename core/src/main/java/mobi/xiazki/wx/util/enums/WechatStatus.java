package mobi.xiazki.wx.util.enums;

/**
 * Created by xiang on 2017/7/2.
 */
public enum  WechatStatus {

    LOGINSTATUS(1,"登陆状态"),
    PROCESSORSTATUS(2,"处理状态"),
    REFRESH(3,"刷新");

    Integer type;

    String text;

    WechatStatus(Integer type, String text) {
        this.type = type;
        this.text = text;
    }

    public Integer type() {
        return type;
    }
}
