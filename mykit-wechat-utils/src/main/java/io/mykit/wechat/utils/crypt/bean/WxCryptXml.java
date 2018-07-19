package io.mykit.wechat.utils.crypt.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import io.mykit.wechat.utils.xml.XStreamCDataConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/19 15:07
 * @Description: 微信加密后的XML字符串
 */
@XStreamAlias("xml")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxCryptXml implements Serializable {
    private static final long serialVersionUID = -8619171153796998295L;

    @XStreamAlias("Encrypt")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String encrypt;

    @XStreamAlias("MsgSignature")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String msgSignature;

    @XStreamAlias("TimeStamp")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String timeStamp;

    @XStreamAlias("Nonce")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String nonce;

}
