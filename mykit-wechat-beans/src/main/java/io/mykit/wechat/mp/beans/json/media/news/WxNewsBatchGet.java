package io.mykit.wechat.mp.beans.json.media.news;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/26 14:16
 * @Description: 获取素材列表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxNewsBatchGet extends BaseJsonBean {
    private static final long serialVersionUID = -1357175048983233242L;
    //素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
    private String type;
    //从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
    private Integer offset;
    //返回素材的数量，取值在1到20之间
    private Integer count;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
