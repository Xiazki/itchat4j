package mobi.xiazki.wx.core;

import mobi.xiazki.wx.factory.Wechat;

import java.util.Map;

/**
 * Created by xiang on 2017/7/1.
 */
public interface WechatContext {

    Map<String, String> getRequestParamMap();

    Map<Integer, Boolean> getStatusMap();

    Wechat getWechat();


}

