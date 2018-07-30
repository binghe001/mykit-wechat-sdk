package io.mykit.wechat.mp.beans.router.receive;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import io.mykit.wechat.mp.beans.xml.receive.base.WxBaseReceiveMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/30 10:29
 * @Description: 微信路由转化的JavaBean结果,实际业务根据routerType中的类型将baseReceiveMessage强制转化为具体的JavaBean
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxReceiveRouterResult extends BaseJsonBean {
    private static final long serialVersionUID = 4429856716411569022L;

    //微信消息路由的依据类
    private String  routerType;
    //实际的路由结果Javabean
    private WxBaseReceiveMessage baseReceiveMessage;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
