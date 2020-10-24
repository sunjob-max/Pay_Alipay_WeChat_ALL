package com.entity;

//import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties(prefix = "snowflake")
public class SystemConfig {

    /** 工作ID (0~31) */
    public static long idworker;

    /** 数据中心ID (0~31) */
    public static long datacenterId;

    public static long getIdworker() {
        return idworker;
    }

    public void setIdworker(long idworker) {
        SystemConfig.idworker = idworker;
    }

    public static long getDatacenterId() {
        return datacenterId;
    }

    public void setDatacenterId(long datacenterId) {
        SystemConfig.datacenterId = datacenterId;
    }
}
