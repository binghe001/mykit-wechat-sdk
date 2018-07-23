package io.mykit.wechat.mp.beans.json.media;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/20 09:44
 * @Description: 上传图文消息条目类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxMediaUploadNewsItem extends BaseJsonBean {

    private static final long serialVersionUID = 994947086295954589L;

    //图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得
    private String thumb_media_id;
    //图文消息的作者
    private String author;
    //图文消息的标题
    private String title;
    //在图文消息页面点击“阅读原文”后的页面，受安全限制，如需跳转Appstore，可以使用itun.es或appsto.re的短链服务，并在短链后增加 #wechat_redirect 后缀。
    private String content_source_url;
    //图文消息页面的内容，支持HTML标签。具备微信支付权限的公众号，可以使用a标签，其他公众号不能使用，如需插入小程序卡片，可参考下文。
    /**
     *
     * 如果需要在群发图文中插入小程序，则在调用上传图文消息素材接口时，需在content字段中添加小程序跳转链接，有以下三种样式的可供选择。
     *
     * 小程序卡片跳转小程序，代码示例：
     *
     * <mp-miniprogram data-miniprogram-appid="wx123123123" data-miniprogram-path="pages/index/index" data-miniprogram-title="小程序示例" data-progarm-imageurl="http://mmbizqbic.cn/demo.jpg"></mp-miniprogram>
     *
     * 文字跳转小程序，代码示例：
     *
     * <p><a data-miniprogram-appid="wx123123123" data-miniprogram-path="pages/index" href="">点击文字跳转小程序</a></p>
     *
     * 图片跳转小程序，代码示例：
     *
     * <p><a data-miniprogram-appid="wx123123123" data-miniprogram-path="pages/index" href=""><img src="http://mmbiz.qpic.cn/mmbiz_jpg/demo/0?wx_fmt=jpg" alt="" data-width="null" data-ratio="NaN"></a></p>
     *
     * 参数说明
     * 参数	是否必须	说明
     * data-miniprogram-appid	是	小程序的AppID
     * data-miniprogram-path	是	小程序要打开的路径
     * data-miniprogram-title	是	小程序卡片的标题，不超过35个字
     * data-miniprogram-imageurl	是	小程序卡片的封面图链接，图片必须为1080*864像素
     */
    private String content;
    //图文消息的描述，如本字段为空，则默认抓取正文前64个字
    private String digest;
    //是否显示封面，1为显示，0为不显示
    private Integer show_cover_pic = 0;
}
