package com.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *  配置文件工具类
 **/
public class ConfigDataUtils {

    /**
     * 系统参数集合
     */
    public static Map<String, String> configData = new HashMap<>(16);


    /**
     * 获取参数
     *
     * @return 返回所有参数
     */
    public static Map<String, String> getAllConfigData() {
        return configData;
    }


    /**
     * 根据键返回值
     *
     * @param key 键
     * @return 返回值
     */
    public static String getConfigByKey(String key) {
        return configData.get(key);
    }


}
