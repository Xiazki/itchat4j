# itchat4j ![java](https://img.shields.io/badge/java-1.8-ff69b4.svg)
微信自动回复机器人

## <a name="Web-Weixin-Pipeline">Web Weixin Pipeline</a>

```
       +--------------+     +---------------+   +---------------+
       |              |     |               |   |               |
       |   Get UUID   |     |  Get Contact  |   | Status Notify |
       |              |     |               |   |               |
       +-------+------+     +-------^-------+   +-------^-------+
               |                    |                   |
               |                    +-------+  +--------+
               |                            |  |
       +-------v------+               +-----+--+------+      +--------------+
       |              |               |               |      |              |
       |  Get QRCode  |               |  Weixin Init  +------>  Sync Check  <----+
       |              |               |               |      |              |    |
       +-------+------+               +-------^-------+      +-------+------+    |
               |                              |                      |           |
               |                              |                      +-----------+
               |                              |                      |
       +-------v------+               +-------+--------+     +-------v-------+
       |              | Confirm Login |                |     |               |
+------>    Login     +---------------> New Login Page |     |  Weixin Sync  |
|      |              |               |                |     |               |
|      +------+-------+               +----------------+     +---------------+
|             |
|QRCode Scaned|
+-------------+
```
## Getting Start
```
 WechatContext wechatContext = new DefaultWechatContext(new ArrayList<MessageHandler>() {
            {
                add(new MessageHandler() {
                    @Override
                    public void handle(WechatMessage wechatMessage) {
                        System.out.println("test");
                    }

                    @Override
                    public Integer getType() {
                        return MessageType.TEXT.getType();
                    }
                });
            }
        });
        Wechat wechat = wechatContext.getWechat();
        wechat.login();
```
