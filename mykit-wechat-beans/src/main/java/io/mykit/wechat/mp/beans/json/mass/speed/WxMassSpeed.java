package io.mykit.wechat.mp.beans.json.mass.speed;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/23 16:38
 * @Description: 设置群发速度参数
 * 群发速度的级别，是一个0到4的整数，数字越大表示群发速度越慢。
 * speed 与 realspeed 的关系如下：
 * speed	realspeed
 * 0	    80w/分钟
 * 1	    60w/分钟
 * 2	    45w/分钟
 * 3	    30w/分钟
 * 4	    10w/分钟
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxMassSpeed extends BaseJsonBean {

    private static final long serialVersionUID = 8871668587589326439L;

    private Integer speed;

    public String toJsonString(){
        return super.toJsonString(this);
    }
}
