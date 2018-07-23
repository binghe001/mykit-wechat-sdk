package io.mykit.wechat.mp.beans.xml.receive.event.mass;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import io.mykit.wechat.utils.xml.XStreamCDataConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/23 15:31
 * @Description: 事件推送群发结果
 */
@XStreamAlias("item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxMassResultListItem implements Serializable {
    private static final long serialVersionUID = -7447549299851024528L;

    @XStreamAlias("ArticleIdx")
    private Integer articleIdx;

    @XStreamAlias("UserDeclareState")
    private Integer userDeclareState;

    @XStreamAlias("AuditState")
    private Integer auditState;

    @XStreamAlias("OriginalArticleUrl")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String originalArticleUrl;

    @XStreamAlias("OriginalArticleType")
    private Integer originalArticleType;

    @XStreamAlias("CanReprint")
    private Integer canReprint;

    @XStreamAlias("NeedReplaceContent")
    private Integer needReplaceContent;

    @XStreamAlias("NeedShowReprintSource")
    private Integer needShowReprintSource;

}
