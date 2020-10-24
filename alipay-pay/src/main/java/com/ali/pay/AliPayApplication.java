package com.ali.pay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class})
@ComponentScan({"com.service.impl","com.redis","com.init"})
@MapperScan({"com.mapper"})
public class AliPayApplication {

    public static void main(String[] args) {

        SpringApplication.run(AliPayApplication.class);
    }
}
