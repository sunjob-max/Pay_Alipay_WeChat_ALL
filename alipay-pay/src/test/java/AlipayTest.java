import com.ali.pay.AliPayApplication;
import com.alibaba.fastjson.JSON;
import com.entity.AjaxResult;
import com.entity.PayRefundRequest;
import com.service.IWebsitePay;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import oshi.jna.platform.windows.Wbemcli;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AliPayApplication.class)
public class AlipayTest {

    @Autowired
    private IWebsitePay alipayService;


    /**
     * @return void
     * @Description 支付宝交易测试
     * @Author lzh
     * @Date 16:38 2018/11/14
     * @Param []
     */
    @Test
    public void alipay() {
        String orderId = new Date().getTime() + "";
        Double amount = 0.01;
        String subject = "test";
        String returnUrl = "http://www.baidu.com";
        try {
            AjaxResult form = alipayService.toPay(orderId, amount, subject, returnUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @return void
     * @Description 订单查询测试
     * @Author lzh
     * @Date 16:38 2018/11/14
     * @Param []
     */
    @Test
    public void alipayOrderQuery() {

        try {
            AjaxResult orderInfo = alipayService.queryOrder("1603550797375");
            System.out.println(JSON.toJSONString(orderInfo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return void
     * @Description 退款
     * @Author lzh
     * @Date 16:38 2018/11/14
     * @Param []
     */
    @Test
    public void alipayOrderRefund() {

        try {
            PayRefundRequest refundRequest = new PayRefundRequest();
            refundRequest.setRefundFee(BigDecimal.valueOf(0.01));
            refundRequest.setOutTradeNo("1603550797375");
            AjaxResult ajaxResult = alipayService.refundOrder(refundRequest);
            System.out.println(ajaxResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 提现至余额
     */
    @Test
    public void test1(){

        AjaxResult ajaxResult = alipayService.withdrawAliPay();
    }

    /**
     * 下载交易记录
     */
    @Test
    public void test2(){
        AjaxResult ajaxResult = alipayService.reconciliationAliPay("2020-10-20");
        System.out.println(ajaxResult);
    }
}
