package test;

import com.utils.ConfigDataUtils;
import com.wechat.pay.WechatApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = WechatApplication.class)
@RunWith(SpringRunner.class)
public class initTest {

    @Test
    public void init(){

        System.out.println(ConfigDataUtils.getConfigByKey("sys.index.skinName"));
        System.out.println(ConfigDataUtils.getConfigByKey("sys.index.skinName"));
        System.out.println(ConfigDataUtils.getConfigByKey("sys.index.skinName"));
        System.out.println(ConfigDataUtils.getConfigByKey("sys.index.skinName"));
        System.out.println(ConfigDataUtils.getConfigByKey("sys.index.skinName"));


    }
}
