package io.mykit.wechat.mp.beans.json.code;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 21:10
 * @Description: 封装微信的返回code
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxCode extends BaseJsonBean {
    private static final long serialVersionUID = -1280036182184419577L;
    //状态码 0：正常，其他：不正常
    private Integer errcode = -1;
    //结果消息
    private String errmsg = "";

    @Override
    public String toString() {
        return super.toString(this);
    }
}
