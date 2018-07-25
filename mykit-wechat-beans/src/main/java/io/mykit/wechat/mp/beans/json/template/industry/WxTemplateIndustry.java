package io.mykit.wechat.mp.beans.json.template.industry;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/25 09:19
 * @Description: 行业
 *
 * 行业代码查询
 * 主行业	            副行业	                    代码
 * IT科技	        互联网/电子商务	                1
 * IT科技	        IT软件与服务	                2
 * IT科技	        IT硬件与设备	                3
 * IT科技	        电子技术	                    4
 * IT科技	        通信与运营商	                5
 * IT科技	        网络游戏	                    6
 * 金融业	            银行	                    7
 * 金融业	        基金理财信托	                8
 * 金融业	            保险	                    9
 * 餐饮	                餐饮	                    10
 * 酒店旅游	            酒店	                    11
 * 酒店旅游	            旅游	                    12
 * 运输与仓储	        快递	                    13
 * 运输与仓储	        物流	                    14
 * 运输与仓储	        仓储	                    15
 * 教育	                培训	                    16
 * 教育	                院校	                    17
 * 政府与公共事业	    学术科研	                18
 * 政府与公共事业	    交警	                    19
 * 政府与公共事业	    博物馆	                    20
 * 政府与公共事业	    公共事业非盈利机构	        21
 * 医药护理	            医药医疗	                22
 * 医药护理	            护理美容	                23
 * 医药护理	            保健与卫生	                24
 * 交通工具	            汽车相关	                25
 * 交通工具	            摩托车相关	                26
 * 交通工具	            火车相关	                27
 * 交通工具	            飞机相关	                28
 * 房地产	            建筑	                    29
 * 房地产	            物业	                    30
 * 消费品	            消费品	                    31
 * 商业服务	            法律	                    32
 * 商业服务	            会展	                    33
 * 商业服务	            中介服务	                34
 * 商业服务	            认证	                    35
 * 商业服务	            审计	                    36
 * 文体娱乐	            传媒	                    37
 * 文体娱乐	            体育	                    38
 * 文体娱乐	            娱乐休闲	                39
 * 印刷	                印刷	                    40
 * 其它	                其它	                    41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxTemplateIndustry extends BaseJsonBean {
    private static final long serialVersionUID = 621303357379767973L;
    //第一行业代码
    private String industry_id1;
    //第二行业代码
    private String industry_id2;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
