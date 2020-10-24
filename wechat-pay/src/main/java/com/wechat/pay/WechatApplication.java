package com.wechat.pay;

import com.entity.Config;
import com.init.ConfigInit;
import com.service.IConfigService;
import com.utils.ConfigDataUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class})
@ComponentScan({"com.service.impl","com.redis","com.init"})
@MapperScan({"com.mapper"})
public class WechatApplication {

    public static void main(String[] args) {

        SpringApplication.run(WechatApplication.class);
    }
}
