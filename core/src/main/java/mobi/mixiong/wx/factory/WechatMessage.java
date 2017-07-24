package mobi.mixiong.wx.factory;

/**
 * Created by xiang on 2017/7/2.
 */
public class WechatMessage {

    private Integer type;

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
