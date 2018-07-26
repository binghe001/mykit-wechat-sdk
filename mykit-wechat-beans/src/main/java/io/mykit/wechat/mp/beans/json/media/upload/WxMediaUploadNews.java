package io.mykit.wechat.mp.beans.json.media.upload;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/20 09:50
 * @Description: 上传图文消息请求体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxMediaUploadNews extends BaseJsonBean {
    //图文消息列表
    private List<WxMediaUploadNewsItem> articles;

    @Override
    public String toString() {
        return super.toString(this);
    }
}
