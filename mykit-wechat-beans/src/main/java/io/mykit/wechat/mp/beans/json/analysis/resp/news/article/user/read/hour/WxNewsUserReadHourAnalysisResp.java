package io.mykit.wechat.mp.beans.json.analysis.resp.news.article.user.read.hour;

import io.mykit.wechat.mp.beans.json.code.WxCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liuyazhuang
 * @version 1.0.0
 * @date 2018/8/6 11:16
 * @description 获取图文统计分时数据接口返回数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxNewsUserReadHourAnalysisResp extends WxCode {

    private static final long serialVersionUID = 6372556949658875721L;

    private List<WxNewsUserReadHourItemAnalysisResp> list;
}
