package io.mykit.wechat.mp.beans.json.mass.tag.video;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/20 11:58
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMassVideoUploadResponseMessage extends BaseJsonBean {
    private static final long serialVersionUID = 832294288132768730L;
    private String type = "video";
    private String media_id;
    private Long created_at;
    @Override
    public String toString() {
        return super.toString(this);
    }
}
