package io.mykit.wechat.mp.beans.json.analysis.news.resp.article.user.read;

import io.mykit.wechat.mp.beans.json.code.WxCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liuyazhuang
 * @version 1.0.0
 * @date 2018/8/6 11:01
 * @description 获取图文统计数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxNewsUserReadAnalysisResp extends WxCode {
    private static final long serialVersionUID = 3097111016673380735L;

    private List<WxNewsUserReadItemAnalysisResp> list;
}
