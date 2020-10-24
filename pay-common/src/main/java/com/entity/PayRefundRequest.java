package com.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description:退款请求对象
 * @Author:pm
 * @DATE:2020-04-09
 */
public class PayRefundRequest implements Serializable {

    private static final long serialVersionUID = -5000639705988879788L;

    /**
     * 退款订单号
     * 支付宝:同一笔订单多次退款则此单号必须唯一
     * 微信:同一笔订单多次退款此单号必须唯一
     */
    public String refundNo;

    /**
     * 支付产生的平台订单号
     */
    public String tradeNo;

    /**
     * 支付产生的第三方生成流水号
     */
    public String outTradeNo;

    /**
     * 订单总金额
     */
    public BigDecimal totalFee;

    /**
     * 退款金额
     */
    public BigDecimal refundFee;

    /**
     * 退款理由
     */
    public String refundReason;


    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(BigDecimal refundFee) {
        this.refundFee = refundFee;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    @Override
    public String toString() {
        return "PayRefundRequest{" +
                "refundNo='" + refundNo + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", totalFee=" + totalFee +
                ", refundFee=" + refundFee +
                ", refundReason='" + refundReason + '\'' +
                '}';
    }
}
