package mobi.xiazki.wx.util.enums;

/**
 * Created by xiang on 2017/7/2.
 */
public enum MessageType {

    TEXT(1, "文字");

    MessageType(Integer type, String text) {
        this.type = type;
        this.text = text;
    }

    private Integer type;

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
