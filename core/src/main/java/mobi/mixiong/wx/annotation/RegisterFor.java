package mobi.mixiong.wx.annotation;

import mobi.mixiong.wx.util.enums.MessageType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xiang on 2017/7/2.
 * 方法注册消息处理器
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RegisterFor {
    MessageType[] type();
}
