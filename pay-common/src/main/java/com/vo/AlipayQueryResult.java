package com.vo;

/**
 * 路由结果集
 */
public class AlipayQueryResult {

    /**
     * * （1）查询成功返回{"code":"10000","msg":"Success","trade_status":"交易状态"}，
     * （2）查询异常返回{"code":"非10000","msg":"错误原因"}
     * （3）交易状态：
     * WAIT_BUYER_PAY（交易创建，等待买家付款）、
     * TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、
     * TRADE_SUCCESS（交易支付成功）、
     * TRADE_FINISHED（交易结束，不可退款）
     */
    private String code;        //查询状态码，查询成功：10000、查询失败：非10000
    private String msg;         //查询结果描述
    private String trade_status;//订单交易状态
    private String subCode;     //查询结果描述，英文版
    private String subMsg;

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTrade_status() {
        return trade_status;
    }

    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status;
    }


}