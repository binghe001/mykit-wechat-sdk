package io.mykit.wechat.utils.exception;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 16:55
 * @Description:  网路相关的异常
 */

public class WxHttpException extends RuntimeException{
    private static final long serialVersionUID = -6798502740962024001L;

    public WxHttpException(){

    }

    public WxHttpException(String message){
        super(message);
    }
}
