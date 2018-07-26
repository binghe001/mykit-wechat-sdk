package io.mykit.wechat.mp.beans.json.media.news;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/26 10:56
 * @Description: 新增永久素材
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxMediaNewsAddItem extends BaseJsonBean {
    private static final long serialVersionUID = 7611995492694079106L;
    //标题
    private String title;
    //图文消息的封面图片素材id（必须是永久mediaID）
    private String thumb_media_id;
    //作者
    private String author;
    //图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空。如果本字段为没有填写，则默认抓取正文前64个字。
    private String digest;
    //是否显示封面，0为false，即不显示，1为true，即显示
    private Integer show_cover_pic = 0;
    //图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS,涉及图片url必须来源 "上传图文消息内的图片获取URL"接口获取。外部图片url将被过滤。
    private String content;
    //图文消息的原文地址，即点击“阅读原文”后的URL
    private String content_source_url;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
