package com.simone.frame.utils.spreference;

/**
 * 当前类注释:当前类用户SharedPreferences进行save的时候 配置key常量
 */
public class SharedPreferencesTag {
    //应用标识
    public static final String TAG = "dlf_store";

    //用户登陆与否的缓存key
    public static final String LOGIN_BOOLEAN = TAG + "login";
    //搜索历史
    public static final String HISTORY_KEY = TAG + "search_history";
    //用户字段
    public static final String USER_PHONE = TAG + "phone";
    public static final String USER_NICKNAME = TAG + "nick_name";
    public static final String USER_TOKEN = TAG + "token";
    public static final String USER_SCORE = TAG + "score";
    public static final String USER_HEAD_IMG = TAG + "head_img";

    //微信支付发起位置(是否是提交订单开启的)
    public static final String isConfirmOrder = TAG + "is_confirm_order";

}
