package io.mykit.wechat.mp.config;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/11 16:48
 * @Description: 储存配置的常量key，本项目中的配置文件可能有多个
 */

public class BaseProp {


    /**---------------------------------各模块的Key-----------------------------------------*/
    /**
     * core模块
     */
    public static final String MODEL_CORE = "model_core";


    /**---------------------------------各种文件的Key-----------------------------------------*/

    /**
     * 配置文件的标识
     */
    public static final String PROP_FILE_URL = "url.properties";
    /**
     * 配置各种参数
     */
    public static final String PROP_FILE_PARAMS = "params.properties";

    /**--------------------------------各种参数的key-------------------------------------------*/

    /**
     * 配置到微信服务器的token
     */
    public static final String WECHAT_TOKEN = "wechat_token";

    /**
     * 设置微信公众号的EncodingAESKey
     */
    public static final String WECHAT_AES_KEY = "wechat_aes_key";


    /**--------------------------------各种微信接口链接的key------------------------------------*/
    /**
     * 获取微信access_token
     */
    public static final String WEIXIN_TOKEN_GET = "weixin.token.get";

    /**
     * 获取微信服务器列表
     */
    public static final String WEIXIN_HOST_GET = "weixin.host.get";

    /**
     * 创建自定义菜单
     */
    public static final String WEIXIN_MENU_CREATE = "wexin.menu.create";

    /**
     * 查询自定义菜单
     */
    public static final String WEIXIN_MENU_GET = "weixin.menu.get";

    /**
     * 删除自定义菜单
     */
    public static final String WEIXIN_MENU_DELETE = "weixin.menu.delete";

    /**
     * 创建个性化菜单
     */
    public static final String WEIXIN_MENU_ADDCONDITIONAL = "wexin.menu.addconditional";
    /**
     * 删除个性化菜单
     */
    public static final String WEIXIN_MENU_DELCONDITIONAL = "weixin.menu.delconditional";
    /**
     * 测试个性化菜单匹配结果
     */
    public static final String WEIXIN_MENU_TRY_MATCH = "wexin.menu.trymatch";
    /**
     * 获取自定义菜单配置
     */
    public static final String WEIXIN_MENU_GET_INFO = "weixin.menu.get.info";

    /**
     *  添加客服账号
     */
    public static final String WEIXIN_MENU_KFACCOUNT_ADD = "weixin.kfaccount.add";
    /**
     * 修改客服账号
     */
    public static final String WEIXIN_MENU_KFACCOUNT_UPDATE = "weixin.kfaccount.update";
    /**
     * 删除客服账号
     */
    public static final String WEIXIN_MENU_KFACCOUNT_DEL = "weixin.kfaccount.del";
    /**
     * 设置客服账号头像
     */
    public static final String WEIXIN_MENU_KFACCOUNT_UPLOADHEADIMG = "weixin.kfaccount.uploadheadimg";
    /**
     * 获取所有客服账号
     */
    public static final String WEIXIN_MENU_KFACCOUNT_GETKFLIST = "weixin.kfaccount.getkflist";

    /**
     * 上传图文消息内的图片获取URL
     */
    public static final String WEIXIN_GROUP_UPLOADIMG = "weixin.group.uploadimg";

}
