package com.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.*;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.entity.AjaxResult;
import com.entity.PayRefundRequest;
import com.service.IWebsitePay;
import com.utils.ConfigDataUtils;
import com.vo.AlipayQueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
public class WebsitePayImpl implements IWebsitePay {


    public AlipayClient alipayClient(){

        AlipayClient alipayClient= new DefaultAlipayClient(ConfigDataUtils.getConfigByKey("alipay_pay_url"), ConfigDataUtils.getConfigByKey("alipay_appid"),
                ConfigDataUtils.getConfigByKey("alipay_private_key"), "json", "utf-8", ConfigDataUtils.getConfigByKey("alipay_public_key"), "RSA2");
        return alipayClient;
    }
    /**
     * 打印日志信息
     */
    private static Logger logger = LoggerFactory.getLogger(WebsitePayImpl.class);

    /**
     * @return java.lang.String 返回完整的表单html
     * @Description 手机网站支付
     * @Param out_trade_no：订单号，最大64位
     * @Param total_amount：订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
     * @Param subject：商品的标题/交易标题/订单标题/订单关键字，如：Iphone6 16G
     * @Param returnUrl：页面回跳地址
     */
    @Override
    public AjaxResult toPay(String out_trade_no, Double total_amount, String subject, String returnUrl) throws Exception {
        //参数校验
        if (StringUtils.isEmpty(out_trade_no)){
            return  AjaxResult.error("订单号为空");
        }
        if (total_amount == null) {
            return  AjaxResult.error("金额无效");
        }
        if (StringUtils.isEmpty(subject)){
            return  AjaxResult.error("商品标题为空");
        }

        //发起支付流程

        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
        alipayRequest.setReturnUrl(returnUrl); //http://domain.com/CallBack/return_url.jsp http://domain.com/CallBack/notify_url.jsp
//        alipayRequest.setNotifyUrl(NOTIFY_URL); //在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                " \"out_trade_no\":\"" + out_trade_no + "\"," +       //订单号，如：20150320010101002
                " \"total_amount\":" + total_amount + "," +       //订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
                " \"subject\":\"" + subject + "\"," +                 //商品的标题/交易标题/订单标题/订单关键字等，如：Iphone6 16G
                " \"product_code\":\"QUICK_WAP_PAY\"" +         //销售产品码，商家和支付宝签约的产品码。该产品请填写固定值：QUICK_WAP_WAY
                " }");

        logger.info("【支付宝手机网站支付-支付】请求参数：[{}]", JSON.toJSONString(alipayRequest));
        String form;
//        try {
            AlipayClient alipayClient = alipayClient();
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
//        } catch (Exception e) {
//            e.printStackTrace();
//            return AjaxResult.error("支付宝SDK调用异常[支付]");
//        }
        logger.info("【支付宝手机网站支付-支付】返回结果：[{}]", form);
        return AjaxResult.success("操作成功",form);
    }

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
     * @Author lzh
     * @Date 20:14 2018/11/5
     * @Param out_trade_no：订单号
     */
    @Override
    public AjaxResult queryOrder(String out_trade_no) throws Exception {
        AlipayQueryResult alipayQueryResult = new AlipayQueryResult();
        //校验订单号
        if (StringUtils.isEmpty(out_trade_no)){
            return AjaxResult.error("订单号为空");
        }
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();

        request.setBizContent("{" + "\"out_trade_no\":\"" + out_trade_no + "\"}");

        logger.info("【支付宝手机网站支付-查询】请求参数：[{}]",JSON.toJSONString(request));

        AlipayTradeQueryResponse response;
        try {
            AlipayClient alipayClient = alipayClient();
            response = alipayClient.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("支付宝SDK调用异常[查询]");
            return AjaxResult.error("网络繁忙请稍后再试，或联系，系统管理员帮助查看");
        }
        logger.info("【支付宝手机网站支付-查询】返回结果：[{}]",JSON.toJSONString(response));
//        if(!StringUtils.isEmpty(response.getCode())){
//            alipayQueryResult.setCode(response.getCode());
//        }
        if(!StringUtils.isEmpty(response.getTradeStatus())){

            String str="";
            if("WAIT_BUYER_PAY".equals(response.getTradeStatus())){
                str ="交易创建，等待买家付款";
            }
            if("TRADE_CLOSED".equals(response.getTradeStatus())){
                str ="未付款交易超时关闭，或支付完成后全额退款";
            }
            if("TRADE_SUCCESS".equals(response.getTradeStatus())){
                str ="交易支付成功";
            }
            if("TRADE_FINISHED".equals(response.getTradeStatus())){
                str ="交易结束，不可退款";
            }

            alipayQueryResult.setTrade_status(str);
        }
        if(!StringUtils.isEmpty(response.getMsg())){
            alipayQueryResult.setMsg(response.getMsg());
        }
        if(!StringUtils.isEmpty(response.getSubMsg())){
            alipayQueryResult.setSubMsg(response.getSubMsg());
        }
        if(!StringUtils.isEmpty(response.getSubCode())){
            alipayQueryResult.setSubCode(response.getSubCode());
        }
        if("10000".equals(response.getCode())){
            return AjaxResult.success("操作成功",alipayQueryResult);
        }
        return AjaxResult.success("操作失败",alipayQueryResult);
    }

    /**
     * 退款请求
     * @param payRefundRequest 退款请对象 其中 退款金额为必填，OutTradeNo  TradeNo二者不可都空
     * @return 退款是否成功
     */
    @Override
    public AjaxResult refundOrder(PayRefundRequest payRefundRequest)  {

        //参数校验

//        OutTradeNo  TradeNo  不可用同时为空
        if (StringUtils.isEmpty(payRefundRequest.getOutTradeNo()) && StringUtils.isEmpty(payRefundRequest.getTradeNo())){
            return  AjaxResult.error("没有订单编号");
        }
        if (null == payRefundRequest.getRefundFee()){
            return  AjaxResult.error("退款金额无效");
        }

        //发起退款
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutTradeNo(payRefundRequest.getOutTradeNo());//我们自己的订单号
        model.setTradeNo(payRefundRequest.getTradeNo());//支付宝交易单号
        model.setRefundAmount(payRefundRequest.getRefundFee()+"");//退款金额
        request.setBizModel(model);

        AlipayTradeRefundResponse response = null;
        try {
            AlipayClient alipayClient = alipayClient();
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            logger.error("发起退款失败");
            e.printStackTrace();
            return AjaxResult.error("网络繁忙请稍后再试，或联系，系统管理员帮助查看");
        }
        if(response.isSuccess()){
            return AjaxResult.success("调用成功",response);
        }
        return AjaxResult.error("调用失败");
    }

    /**
     * 异步通知
     *
     * @param map
     * @return 返回json
     * （1）查询成功返回{"code":"10000","msg":"Success","trade_status":"交易状态"}，
     * （2）查询异常返回{"code":"非10000","msg":"错误原因"}
     * （3）交易状态：
     * WAIT_BUYER_PAY（交易创建，等待买家付款）、
     * TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、
     * TRADE_SUCCESS（交易支付成功）、
     * TRADE_FINISHED（交易结束，不可退款）
     * @throws Exception
     */
    @Override
    public AjaxResult notify(Map<String, String> map) {
        JSONObject jsonObject = new JSONObject();
        try {
            //获取所有参数
            System.out.println("【手机网站支付-异步通知】请求参数："+ JSON.toJSONString(map));
            //签名校验，校验不通过丢弃
            /*
            if(!AlipaySignature.rsaCheckV1(map,ALIPAY_PUBLIC_KEY,map.get("charset"),map.get("sign_type"))){
                logger.error("【手机网站支付-异步通知】签名无效，丢弃");
                throw AliPayBizException.ALIPAY_SIGNATURE_INVALID;
            }
            */
            //调用业务处理流程
            Assert.hasLength(map.get("out_trade_no"), "订单号为空");

            jsonObject.put("channelOrderId", map.get("out_trade_no"));
            if ("TRADE_SUCCESS".equals(map.get("trade_status"))) {
                jsonObject.put("msg", "交易成功");
                return AjaxResult.success(jsonObject);
            } else {
                jsonObject.put("msg", "交易失败");
                return AjaxResult.error("交易失败");
            }
            //查询成功才返回交易状态
        } catch (Exception e) {
            System.out.println("【手机网站支付-异步通知】异常");
            e.printStackTrace();
            return AjaxResult.error("网络繁忙请稍后再试，或联系，系统管理员帮助查看");
        }
    }


    /**
     * 提现至零钱，暂无权限
     * @return
     */
    @Override
    public AjaxResult withdrawAliPay(){
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        String orderId = System.currentTimeMillis() + "";
        request.setBizContent("{" +

                "\"out_biz_no\":\""+orderId+"\"," +
                "\"payee_type\":\"ALIPAY_LOGONID\"," +
                "\"payee_account\":\"17373348393\"," +
                "\"amount\":\"0.01\"," +
                "\"payer_show_name\":\"长沙八骏信息科技有限公司\"," +
                "\"payee_real_name\":\"liuqiyou\"," +
                "\"remark\":\"转账备注\"" +
                "  }");
        AlipayFundTransToaccountTransferResponse response = null;
        try {
            AlipayClient alipayClient = alipayClient();
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return null;
    }

    /**
     * 下载支付宝交易记录对账
     * @param time 参数格式：yyyy-MM-dd   至少是当天的前一天
     * @return bill_download_url 这个链接需在30秒内去下载，否则就无效了
     */
    @Override
    public AjaxResult reconciliationAliPay(String time){

        AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();//创建API对应的request类
        request.setBizContent("{" +
                "\"bill_type\":\"trade\"," +
                "\"bill_date\":\""+time+"\"}"); //设置业务参数
        AlipayDataDataserviceBillDownloadurlQueryResponse response = null;//通过alipayClient调用API，获得对应的response类

        try {
            AlipayClient alipayClient = alipayClient();
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            logger.error("支付宝下载交易记录失败");
            e.printStackTrace();
            return AjaxResult.error("网络繁忙请稍后再试，或联系，系统管理员帮助查看");
        }
        //根据response中的结果继续业务逻辑处理

        if("10000".equals(response.getCode())){

            return AjaxResult.success("操作成功(需要30秒内使用下载链接)",response.getBody());
        }
        if("入参不合法".equals(response.getSubMsg())){

            return AjaxResult.error("需要下载的账单日期，最晚是当期日期的前一天。");
        }

        return AjaxResult.error("下载交易记录失败");
    }

}
