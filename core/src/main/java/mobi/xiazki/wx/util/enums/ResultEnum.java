package mobi.xiazki.wx.util.enums;

/**
 * Created by xiang on 2017/7/2.
 */
public enum ResultEnum {
    SUCCESS("200", "成功"),
    WAIT_CONFIRM("201", "确认"),
    WAIT_SCAN("400", "扫描二维码");

    private String code;
    private String text;

    ResultEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }
}
