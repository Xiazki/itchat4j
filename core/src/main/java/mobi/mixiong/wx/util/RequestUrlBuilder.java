package mobi.mixiong.wx.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import mobi.mixiong.wx.constant.Constants;
import mobi.mixiong.wx.util.enums.RequestEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by xiang on 2017/7/1.
 */
public class RequestUrlBuilder {

    public static final String WECHAT_URL = "https://login.weixin.qq.com";
    /**
     * get UUID
     */
    public static final String UUID_URL = WECHAT_URL + "/jslogin";

    /**
     * 微信状态通知
     */
    public static final String STATUS_NOTIFY_URL = WECHAT_URL + "/webwxstatusnotify?lang=zh_CN&pass_ticket=%s";

    public static final String QR_URL = WECHAT_URL + "/qrcode/";


    /**
     * 登陆URL
     */
    public static final String LOGIN_URL = WECHAT_URL + "/cgi-bin/mmwebwx-bin/login";

    /**
     * 微信初始化
     */
    public static final String INIT_URL = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxinit";


    public static String buildGetUUIDUrl(Map<String, String> map) {
        return UUID_URL + "?appid=" + map.get(Constants.APPID) + "&fun=" + map.get(Constants.FUN) + "&lang=" + map.get(Constants.LANG) + "&_=" + System.currentTimeMillis();
    }

    public static String buildLoginURL(String uuid) {
        String um = String.valueOf(System.currentTimeMillis());
        return LOGIN_URL + "?tip=0&uuid=" + uuid + "&_=" + um;
    }

    public static String buildWxInitURL(Map<String, String> map) {
        return INIT_URL + "?r=" + String.valueOf(System.currentTimeMillis() / 3158L) + "&pass_ticket=" + map.get(RequestEnum.PASS_TICKET.getValue());

    }

    public static JSONObject buildBaseRequest(Map<String, String> map) {
        JSONObject stringMap = new JSONObject();
        stringMap.put("Uin", map.get(RequestEnum.WXUIN.getValue()));
        stringMap.put("Sid", map.get(RequestEnum.WXSID.getValue()));
        stringMap.put("Skey", map.get(RequestEnum.SKEY.getValue()));
        stringMap.put("DeviceID", map.get(RequestEnum.PASS_TICKET.getValue()));
        return stringMap;
    }

    public static String buildWxStatusNotify(Map<String, String> map) {
        String retUrl = String.format(STATUS_NOTIFY_URL, map.get(RequestEnum.PASS_TICKET.getValue()));
        return retUrl;
    }
}
