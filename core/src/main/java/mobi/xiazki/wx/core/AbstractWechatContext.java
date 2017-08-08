package mobi.xiazki.wx.core;

import mobi.xiazki.wx.constant.Constants;
import mobi.xiazki.wx.factory.*;
import mobi.xiazki.wx.util.enums.WechatStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by xiang on 2017/7/2.
 */
public abstract class AbstractWechatContext implements WechatContext {


    /**
     * 可以通过其他方式注入
     */
    protected LifecycleProcessor lifecycleProcessor;
    protected MessageProcessor messageProcessor;

    private WechatFactoryBean factoryBean;

    private Map<String, String> param;

    private Map<Integer, Boolean> statusMap = new HashMap<Integer, Boolean>() {
        {
            put(WechatStatus.LOGINSTATUS.type(), false);
            put(WechatStatus.PROCESSORSTATUS.type(), true);
            put(WechatStatus.REFRESH.type(), false);
        }
    };

    public AbstractWechatContext() {
        //默认
        param = new HashMap<String, String>() {
            {
                put(Constants.APPID, "wx782c26e4c19acffb");
                put(Constants.FUN, "new");
                put(Constants.LANG, "zh_CN");
                put(Constants.FILEPATH,"C:\\Users\\xiang\\Desktop");
            }
        };
        factoryBean = new DefaultWechatFactoryBean(this);

        initProcessor();
        beginWechatProcess();

    }

    /**
     * 初始化processor
     */
    protected void initProcessor() {
        this.lifecycleProcessor = new DefaultLifecycleProcessor(this);
        // messageProcessor
    }


    private void beginWechatProcess() {
        Executor executor = getExecutor();
        executor.execute(() -> {
            while (statusMap.get(WechatStatus.PROCESSORSTATUS.type())) {

                if (!statusMap.get(WechatStatus.LOGINSTATUS.type())) {
                    try {
                        lifecycleProcessor.login();
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }

                if (statusMap.get(WechatStatus.REFRESH.type())) {
//                    messageProcessor.onRefresh();
//                    lifecycleProcessor.onRefresh();
                    System.out.println("开始刷新微信状态");
                    System.out.println("开始刷新消息");

                }
            }
        });

        executor.execute(() -> {
            while (statusMap.get(WechatStatus.PROCESSORSTATUS.type())) {
                if (statusMap.get(WechatStatus.LOGINSTATUS.type())) {
                    WechatMessage message = getMessageProduce().getMsg();
                    HandlerMap handlerMap = getHandlerMap();
                    if (message != null && handlerMap != null) {
                        List<MessageHandler> handlers = handlerMap.getMessageHandler(message.getType());
                        for (MessageHandler handler : handlers) {
                            handler.handle(message);
                        }
                    }
                }
            }
        });
    }

    public Wechat getWechat() {
        return factoryBean.getWechat();
    }

    public Executor getExecutor() {
        return Executors.newFixedThreadPool(5);
    }

    public abstract HandlerMap getHandlerMap();

    public abstract MessageProduce getMessageProduce();

    public Map<String, String> getRequestParamMap() {
        return param;
    }

    public Map<Integer, Boolean> getStatusMap() {
        return statusMap;
    }
}
