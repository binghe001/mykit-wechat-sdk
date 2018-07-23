package io.mykit.wechat.mp.beans.json.media;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/20 09:17
 * @Description: 上传多媒体文件返回的结果数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMediaUploadResponseBean extends BaseJsonBean {
    private static final long serialVersionUID = 8219812894814406429L;

    private String type;
    private String media_id;
    private Long create_at;

}
