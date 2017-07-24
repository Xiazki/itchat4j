package mobi.mixiong.wx.util.enums;

/**
 * Created by xiang on 2017/7/19.
 */
public enum RequestEnum {

    SKEY("skey", "skey"),
    WXSID("wxsid", "wxsid"),
    WXUIN("wxuin", "wxuin"),
    PASS_TICKET("pass_ticket", "pass_ticket");

    RequestEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    private String text;
    private String value;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
