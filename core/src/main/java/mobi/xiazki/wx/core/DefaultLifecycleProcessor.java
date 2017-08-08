package mobi.xiazki.wx.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import mobi.xiazki.wx.constant.Constants;
import mobi.xiazki.wx.factory.Wechat;
import mobi.xiazki.wx.http.MXHttpClient;
import mobi.xiazki.wx.util.RequestUrlBuilder;
import mobi.xiazki.wx.util.enums.RequestEnum;
import mobi.xiazki.wx.util.enums.ResultEnum;
import mobi.xiazki.wx.util.enums.ResultTools;
import mobi.xiazki.wx.util.enums.WechatStatus;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import java.io.*;
import java.util.Map;

/**
 * Created by xiang on 2017/7/2.
 * 需要改成抽象类 业务不复杂还是算了
 */
public class DefaultLifecycleProcessor implements LifecycleProcessor {

    private Logger logger = LoggerFactory.getLogger(DefaultLifecycleProcessor.class);

    private WechatContext wechatContext;

    private Wechat wechat;

    public DefaultLifecycleProcessor(WechatContext wechatContext) {
        setWchatContext(wechatContext);
    }


    @Override
    public void login() throws Exception {
        beforeLogin();
        boolean flag = true;
        while (flag) {
            String loginUrl = RequestUrlBuilder.buildLoginURL(wechatContext.getRequestParamMap().get("uuid"));
            String result = MXHttpClient.getString(loginUrl);
            if (result != null) {
                Map<String, String> loginParam = ResultTools.processResult(result);
                if (ResultEnum.SUCCESS.getCode().equals(loginParam.get("window.code"))) {
                    System.out.println("login success");
                    afterLogin(loginParam);
                    flag = false;
                }
            }
        }
        //开启微信状态通知
        wxStatusNotify();
        //开始刷新微信
        wechatContext.getStatusMap().put(WechatStatus.REFRESH.type(), true);

    }

    /**
     * @return 可以改成写在抽象类的
     */
    private void beforeLogin() throws Exception {
        boolean flag = true;
        String requestUUID = RequestUrlBuilder.buildGetUUIDUrl(wechatContext.getRequestParamMap());
        while (flag) {
            //获取UUID; 改成抽象类由子类实现
            String result = MXHttpClient.getString(requestUUID);
            if (result == null) {
                System.out.println("获取UUID失败");
                logger.info("获取UUID失败");
                continue;
            }
            Map<String, String> map = ResultTools.processResult(result);
            if (!(ResultEnum.SUCCESS.getCode().equals(map.get("window.QRLogin.code")))) {
                continue;
            }
            String uuid = map.get("window.QRLogin.uuid");
            wechatContext.getRequestParamMap().put("uuid", uuid);

            //二维码登陆
            String path = getQr(uuid);
            openPic(path);
            flag = false;
        }
    }

    private void afterLogin(Map<String, String> loginParam) throws IOException {
        String uri = loginParam.get("window.redirect_uri");
        //处理参数
        HttpEntity entity = MXHttpClient.get(uri);
        String text = EntityUtils.toString(entity);
        Document doc = ResultTools.xmlParser(text);
        //微信初始化
        if (doc != null) {
            wechatContext.getRequestParamMap().put(RequestEnum.SKEY.getValue(), doc.getElementsByTagName(RequestEnum.SKEY.getValue()).item(0).getFirstChild()
                    .getNodeValue());
            wechatContext.getRequestParamMap().put(RequestEnum.WXSID.getValue(), doc.getElementsByTagName(RequestEnum.WXSID.getValue()).item(0).getFirstChild()
                    .getNodeValue());
            wechatContext.getRequestParamMap().put(RequestEnum.WXUIN.getValue(), doc.getElementsByTagName(RequestEnum.WXUIN.getValue()).item(0).getFirstChild()
                    .getNodeValue());
            wechatContext.getRequestParamMap().put(RequestEnum.PASS_TICKET.getValue(), doc.getElementsByTagName(RequestEnum.PASS_TICKET.getValue()).item(0).getFirstChild()
                    .getNodeValue());
        }

        String initUrl = RequestUrlBuilder.buildWxInitURL(wechatContext.getRequestParamMap());
        JSONObject baseRequest = RequestUrlBuilder.buildBaseRequest(wechatContext.getRequestParamMap());
        JSONObject param = new JSONObject();
        param.put("BaseRequest", baseRequest);
        HttpEntity reEntity = MXHttpClient.post(initUrl, param.toJSONString());
        String jsonStr = EntityUtils.toString(reEntity);
        JSONObject retObj = JSON.parseObject(jsonStr);
        JSONObject userObj = retObj.getJSONObject("User");
        wechatContext.getRequestParamMap().put(Constants.USERNAME, userObj.getString(Constants.USERNAME));
    }

    /**
     * 微信状态通知
     */
    private void wxStatusNotify() throws IOException {
        String url = RequestUrlBuilder.buildWxStatusNotify(wechatContext.getRequestParamMap());
        JSONObject pram = new JSONObject();
        pram.put(Constants.CODE, "3");
        pram.put(Constants.FROM_USERNAME, wechatContext.getRequestParamMap().get(Constants.USERNAME));
        pram.put(Constants.TO_USERNAME, wechatContext.getRequestParamMap().get(Constants.USERNAME));
        pram.put(Constants.CLIENT_MSG_ID, System.currentTimeMillis());

        HttpEntity entity = MXHttpClient.post(url, pram.toJSONString());
        try {
            EntityUtils.toString(entity);
        } catch (IOException e) {
            throw new IOException("开启微信状态通知失败！");
        }
    }

    private void openPic(String path) {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("cmd /c start " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //改成有子类实现
    private String getQr(String uuid) throws Exception {
        String path = wechatContext.getRequestParamMap().get(Constants.FILEPATH);
        if (path == null) {
            throw new Exception("请配置QR路径");
        }
        String qrPath = path + File.separator + "QR.jpg";
        //访问需要统一
        HttpEntity httpEntity = MXHttpClient.get(RequestUrlBuilder.QR_URL + uuid);
        OutputStream out = new FileOutputStream(qrPath);
        assert httpEntity != null;
        byte[] bytes = EntityUtils.toByteArray(httpEntity);
        if (bytes.length <= 0) {
            System.out.println("获取二维码失败！");
            throw new Exception("获取二维码失败");
        }
        out.write(bytes);
        out.flush();
        out.close();

        return qrPath;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onClose() {

    }

    @Override
    public void setWchatContext(WechatContext wchatContext) {
        this.wechatContext = wchatContext;
    }

}
