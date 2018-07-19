package io.mykit.wechat.mp.beans.json.base;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 14:37
 * @Description: 从基础的JsonBean
 */

public class BaseJsonBean implements Serializable {

    private static final long serialVersionUID = -1446274398967377397L;

    public String toString(Object obj){
        return JSONObject.toJSONString(obj);
    }

}
