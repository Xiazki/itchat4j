package mobi.mixiong.wx.core;

import mobi.mixiong.wx.factory.Wechat;
import mobi.mixiong.wx.factory.WechatMessage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * Created by xiang on 2017/7/1.
 */
public interface WechatContext {

    Map<String, String> getRequestParamMap();

    Map<Integer, Boolean> getStatusMap();

    Wechat getWechat();


}

