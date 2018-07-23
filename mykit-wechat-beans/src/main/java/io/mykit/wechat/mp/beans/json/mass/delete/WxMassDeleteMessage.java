package io.mykit.wechat.mp.beans.json.mass.delete;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/23 13:27
 * @Description:  删除群发消息
 * 1、只有已经发送成功的消息才能删除
 * 2、删除消息是将消息的图文详情页失效，已经收到的用户，还是能在其本地看到消息卡片。
 * 3、删除群发消息只能删除图文消息和视频消息，其他类型的消息一经发送，无法删除。
 * 4、如果多次群发发送的是一个图文消息，那么删除其中一次群发，就会删除掉这个图文消息也，导致所有群发都失效
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMassDeleteMessage extends BaseJsonBean {
    private static final long serialVersionUID = 2619151913293065513L;
    //发送出去的消息ID
    private String msg_id;
    //要删除的文章在图文消息中的位置，第一篇编号为1，该字段不填或填0会删除全部文章
    private Integer article_idx;

}
