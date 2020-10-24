package com.init;

import com.entity.Config;
import com.google.common.collect.Maps;
import com.service.IConfigService;
import com.utils.ConfigDataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化系统参数
 */
@Component
public class ConfigInit implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * 打印日志信息
     */
    private static Logger accessLog = LoggerFactory.getLogger(ConfigInit.class);

    @Autowired
    private IConfigService iConfigService;

    @PostConstruct
    public void init(){
        List<Config> configs = iConfigService.selectConfigList(new Config());
        Map<String, String> configData = new HashMap<>(16);
        for (Config config : configs) {
            configData.put(config.getConfigKey(), config.getConfigValue());
        }
        //初始化系统参数
        ConfigDataUtils.configData = configData;
        accessLog.info("=================系统参数初始化成功!!!!!!!=========================");

    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<Config> configs = iConfigService.selectConfigList(new Config());
        Map<String, String> configData = new HashMap<>(16);
        for (Config config : configs) {
            configData.put(config.getConfigKey(), config.getConfigValue());
        }
        //初始化系统参数
        ConfigDataUtils.configData = configData;
        accessLog.info("=================系统参数初始化成功!!!!!!!=========================");
    }

    @Resource
    private Environment env;

    @Resource
    private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        if(viewResolver != null) {
            Map<String, Object> vars = Maps.newHashMap();
            vars.put("globalImgUrl", env.getProperty("mh.imgUrl"));
            viewResolver.setStaticVariables(vars);
        }
    }

}
