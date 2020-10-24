package com.constant;

/**
 * 通用常量信息
 */
public class SystemConstants {

    /** 缓存个人信息 */
    public static final String REDIS_USER = "user:";

    /** 缓存商户信息 */
    public static final String REDIS_MERCHANT = "merchant:";

    /** 用户token */
    public static final String TOKEN = "token";

    /* 商家语言播报 setGetuiKey */
    public static final String GETUI_KEY = "merchant:getui:";

    /* 商家语言播报 setGetuiKey */
    public static final String REDIS_STUDENT = "studentInfo:stu";

    public static final String SHOP_QRCODE = "shopQrCode:";

    public static final String REDIS_BALANCE = "balance:";

    /** 用户是否启用    1启用  2停用 **/
    public final static String IS_ON="1";
    /** 用户是否启用    1启用  2停用 **/
    public final static String IS_OFF="2";

    /** 注册类型    1微信公众号，2支付宝生活号**/
    public final static String REG_TYPE_WX="1";
    /** 注册类型    1微信公众号，2支付宝生活号**/
    public final static String REG_TYPE_ZFB="2";

    /** 用户类型 0未知，1学生/职工,2商户**/
    public final static String BJ_TYPE_ZERO="0";
    /** 用户类型 0未知，1学生/职工,2商户**/
    public final static String BJ_TYPE_ONE="1";
    /** 用户类型 0未知，1学生/职工,2商户**/
    public final static String BJ_TYPE_TWO="2";

    /** 0:主动关注并进行了实名，1:SHOP_ID 扫码关注 */
    public final static String REG_SOURCE_ZERO="0";
    /** 0:主动关注并进行了实名，1:SHOP_ID 扫码关注 */
    public final static String REG_SOURCE_ONE="1";

    /** 变动类型,1 流入 2 流出  */
    public final static String CHANGE_TYPE_IN="1";
    /** 变动类型,1 流入 2 流出  */
    public final static String CHANGE_TYPE_OUT="2";

    /** 支付结果，0已支付，1支付结果未知，2支付失败,3未支付  */
    public final static String PAY_STATUS_ZERO="0";
    /** 支付结果，0已支付，1支付结果未知，2支付失败,3未支付  */
    public final static String PAY_STATUS_ONE="1";
    /** 支付结果，0已支付，1支付结果未知，2支付失败,3未支付  */
    public final static String PAY_STATUS_TWO="2";
    /** 支付结果，0已支付，1支付结果未知，2支付失败,3未支付  */
    public final static String PAY_STATUS_THREE="3";

    /** 支付类型，1微信支付 2支付宝支付 */
    public final static String PAY_TYPE_WX="1";
    /** 支付类型，1微信支付 2支付宝支付 */
    public final static String PAY_TYPE_ZFB="2";

    /**微信 回调地址**/
    public final static String WX_NOTIFY_URL="/order/pay/callback/callBackWechatSubPay";

    /** 业务渠道，0食堂收费系统，1水电缴费 **/
    public final static String BUS_CHANNEL_ZERO="0";
    /** 业务渠道，0食堂收费系统，1水电缴费 **/
    public final static String BUS_CHANNEL_ONE="1";

    /** 流水类型,1 食堂消费 2 充值 3 提现 4 回款 5 人工冲正 */
    public final static String FLW_TYPE_ONE="1";
    /** 流水类型,1 食堂消费 2 充值 3 提现 4 回款 5 人工冲正 */
    public final static String FLW_TYPE_TWO="2";
    /** 流水类型,1 食堂消费 2 充值 3 提现 4 回款 5 人工冲正 */
    public final static String FLW_TYPE_THREE="3";
    /** 流水类型,1 食堂消费 2 充值 3 提现 4 回款 5 人工冲正 */
    public final static String FLW_TYPE_FOUR="4";
    /** 流水类型,1 食堂消费 2 充值 3 提现 4 回款 5 人工冲正 */
    public final static String FLW_TYPE_FIVE="5";

    /** 提现到哪 1 微信零钱包 2 银行卡 */
    public final static String withdrawToAccount_ONE = "1";
    /** 提现到哪 1 微信零钱包 2 银行卡 */
    public final static String withdrawToAccount_TWO = "2";

    /** 提现状态 1已发起，2提现成功，3提现失败,4提现结果未知 */
    public final static Integer withdrawToAccountState_ONE = 1;
    /** 提现状态 1已发起，2提现成功，3提现失败,4提现结果未知 */
    public final static Integer withdrawToAccountState_TWO = 2;
    /** 提现状态 1已发起，2提现成功，3提现失败,4提现结果未知 */
    public final static Integer withdrawToAccountState_THREE = 3;
    /** 提现状态 1已发起，2提现成，3提现失败,4提现结果未知 */
    public final static Integer withdrawToAccountState_FOUR = 4;


    /** 支付渠道 0长沙银行，1微信，2支付宝 */
    public final static String withdrawPayCHANNEL_ZROE = "0";
    /** 支付渠道 0长沙银行，1微信，2支付宝 */
    public final static String withdrawPayCHANNEL_ONE = "1";
    /** 支付渠道 0长沙银行，1微信，2支付宝 */
    public final static String withdrawPayCHANNEL_TWO = "2";

    /** 提现方式 0 手动 1 自动 */
    public final static String withdrawTYPE_ZROE = "0";
    /** 提现方式 0 手动 1 自动 */
    public final static String withdrawTYPE_ONE = "1";

    /** 审核状态，0未提交，1已提交待审核，2已审核，审核通过，3已审核，审核未通过 */
    public final static String AUDIT_STATUS_ZROE = "0";
    /** 审核状态，0未提交，1已提交待审核，2已审核，审核通过，3已审核，审核未通过 */
    public final static String AUDIT_STATUS_ONE = "1";
    /** 审核状态，0未提交，1已提交待审核，2已审核，审核通过，3已审核，审核未通过 */
    public final static String AUDIT_STATUS_TWO = "2";
    /** 审核状态，0未提交，1已提交待审核，2已审核，审核通过，3已审核，审核未通过 */
    public final static String AUDIT_STATUS_THREE = "3";

}
