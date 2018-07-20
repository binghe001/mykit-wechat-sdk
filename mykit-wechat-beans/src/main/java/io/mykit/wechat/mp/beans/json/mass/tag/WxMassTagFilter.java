package io.mykit.wechat.mp.beans.json.mass.tag;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/20 10:18
 * @Description: 根据标签进行群发的Filter
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMassTagFilter extends BaseJsonBean {
    private static final long serialVersionUID = 4120705959233993332L;
    //用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据tag_id发送给指定群组的用户
    private Boolean is_to_all;
    //群发到的标签的tag_id，参见用户管理中用户分组接口，若is_to_all值为true，可不填写tag_id
    private Integer tag_id;
}
