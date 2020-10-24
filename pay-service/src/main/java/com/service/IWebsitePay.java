package com.service;


import com.entity.AjaxResult;
import com.entity.PayRefundRequest;

import java.util.Map;

/**
 * @Description 支付宝——手机网站支付
 * @ClassName IWebsitePay
 * @Author lzh
 * @Date 2018/11/5 15:30
 * @Version 1.0
 */
public interface IWebsitePay {

    /**
     * @return java.lang.String 返回完整的表单html
     * @Param out_trade_no：订单号，最大64位
     * @Param total_amount：订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
     * @Param subject：商品的标题/交易标题/订单标题/订单关键字，如：Iphone6 16G
     * @Param returnUrl：页面回跳地址
     */
    public AjaxResult toPay(String out_trade_no, Double total_amount, String subject, String returnUrl) throws Exception;

    /**
     * @return 返回json
     * （1）查询成功返回{"code":"10000","msg":"Success","trade_status":"交易状态"}，
     * （2）查询异常返回{"code":"非10000","msg":"错误原因"}
     * （3）交易状态：
     * WAIT_BUYER_PAY（交易创建，等待买家付款）、
     * TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、
     * TRADE_SUCCESS（交易支付成功）、
     * TRADE_FINISHED（交易结束，不可退款）
     * @Description 订单查询
     * @Param out_trade_no：订单号
     */
    public AjaxResult queryOrder(String out_trade_no) throws Exception;

    /**
     * 返回
     * @param payRefundRequest
     * @return
     */
    public AjaxResult refundOrder(PayRefundRequest payRefundRequest);

    /**
     * @param map
     * @return
     * @throws Exception
     */
    public AjaxResult notify(Map<String, String> map);

    /**
     * 提现公司账户至零钱，暂无权限
     * @return
     */
    public AjaxResult withdrawAliPay();

    /**
     * 下载支付宝交易记录，对账
     * @return
     */
    public AjaxResult reconciliationAliPay(String time);
}
