package io.mykit.wechat.mp.beans.json.mass.get;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/23 14:41
 * @Description: 查询群发消息发送状态
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxMassGetMessage extends BaseJsonBean {
    private static final long serialVersionUID = 6055269674671124017L;

    private String msg_id;

    @Override
    public String toString() {
        return super.toString(this);
    }
}
