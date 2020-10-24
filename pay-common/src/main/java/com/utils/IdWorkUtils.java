
package com.utils;


import com.entity.SystemConfig;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.TreeSet;

/**
 * 分布式id主键
 *
 * @author pm
 */
@Component
public class IdWorkUtils {

// ==============================Fields===========================================
    /**
     * 开始时间截 (2015-01-01)
     */
    private static final long TWEPOCH = 1420041600000L;

    /**
     * 机器id所占的位数
     */
    private static final long WORKER_ID_BITS = 5L;

    /**
     * 数据标识id所占的位数
     */
    private static final long DATACENTER_ID_BITS = 5L;

    /**
     * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private static final long MAX_WORKER_ID = -1L ^ (-1L << WORKER_ID_BITS);

    /**
     * 支持的最大数据标识id，结果是31
     */
    private static final long MAX_DATACENTER_ID = -1L ^ (-1L << DATACENTER_ID_BITS);

    /**
     * 序列在id中占的位数
     */
    private static final long SEQUENCE_BITS = 12L;

    /**
     * 机器ID向左移12位
     */
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;

    /**
     * 数据标识id向左移17位(12+5)
     */
    private static final long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    /**
     * 时间截向左移22位(5+5+12)
     */
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private static final long SEQUENCE_MASK = -1L ^ (-1L << SEQUENCE_BITS);

    /**
     * 工作机器ID(0~31)
     */
    private static long WORKER_ID;

    /**
     * 数据中心ID(0~31)
     */
    private static long DATACENTER_ID;

    /**
     * 毫秒内序列(0~4095)
     */
    private static long SEQUENCE = 0L;

    /**
     * 上次生成ID的时间截
     */
    private static long LAST_TIMESTAMP = -1L;

    //==============================Constructors=====================================
    /**
     * 构造函数
     */
    public IdWorkUtils() {
        if (SystemConfig.idworker > MAX_WORKER_ID || SystemConfig.idworker < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", MAX_WORKER_ID));
        }
        if (SystemConfig.datacenterId > MAX_DATACENTER_ID || SystemConfig.datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", MAX_DATACENTER_ID));
        }
        IdWorkUtils.WORKER_ID = SystemConfig.idworker;
        IdWorkUtils.DATACENTER_ID = SystemConfig.datacenterId;
    }

    // ==============================Methods==========================================

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public static synchronized String nextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < LAST_TIMESTAMP) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", LAST_TIMESTAMP - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (LAST_TIMESTAMP == timestamp) {
            SEQUENCE = (SEQUENCE + 1) & SEQUENCE_MASK;
            //毫秒内序列溢出
            if (SEQUENCE == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(LAST_TIMESTAMP);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            SEQUENCE = 0L;
        }

        //上次生成ID的时间截
        LAST_TIMESTAMP = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return new Long(((timestamp - TWEPOCH) << TIMESTAMP_LEFT_SHIFT)
                | (DATACENTER_ID << DATACENTER_ID_SHIFT)
                | (WORKER_ID << WORKER_ID_SHIFT)
                | SEQUENCE).toString();
    }

    /**
     * 生成主键编号
     *
     * @return
     */
    public static long generateId() {
        long nano = Math.abs((System.nanoTime() % 1000));
        return Long.parseLong(System.currentTimeMillis() + String.format("%0" + 3 + "d", nano));
    }

    /**
     * 生成主键编号
     *
     * @return
     */
    public static long generateId2() {
        long nano = Math.abs((System.nanoTime() % 100));
        return Long.parseLong(System.currentTimeMillis() + String.format("%0" + 2 + "d", nano));
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected static long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        Set<String> setData = new TreeSet<String>();
        int maxNum = 4000000;
        for (int i=0; i<maxNum; i++){
            String id = IdWorkUtils.nextId();
            setData.add(id);
        }
        System.out.printf("setData====" + setData.size());
    }


}
