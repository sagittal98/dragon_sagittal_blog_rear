package com.dragon.sagittal.blog.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.dragon.sagittal.blog.common.config.YmlConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 一个分布式的id发号器，不依赖数据库
 * 根据twitter的SnowFlake改写成java版本
 * 该类为一个单例模式，实例化方法如下：
 * IdWorkerSingleton id = IdWorkerSingleton.getInstance(0L, 0L);
 * 获取一个id：
 * id.nextId();
 */
public class IdWorkerSingleton {
    private static volatile IdWorkerSingleton instance;

    /**
     * 起始时间戳2018/11/01 00:00:00
     */
    private static final long START_TIMESTAMP = 1541001600000L;
    /**
     * 机器标识占用的位数
     */
    private static final long MACHINE_BIT = 5L;
    /**
     * 数据中心占用的位数
     */
    private static final long DATACENTER_BIT = 5L;
    /**
     * 序列号占用的位数
     */
    private static final long SEQUENCE_BIT = 12L;
    /**
     * 数据中心最大值
     */
    private static final long MAX_DATACENTER_NUM = ~(-1L << DATACENTER_BIT);
    /**
     * 机器标识最大值
     */
    private static final long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    /**
     * 序列号最大值
     */
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);
    /**
     * 机器id向左移12位
     */
    private static final long MACHINE_LEFT = SEQUENCE_BIT;
    /**
     * 数据标识id向左移17位（12+5）
     */
    private static final long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    /**
     * 时间戳向左移22位（12+5+5）
     */
    private static final long TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATACENTER_BIT;
    /**
     * 数据中心
     */
    private  long dataCenterId;
    /**
     * 机器标识
     */
    private  long machineId;
    /**
     * 序列号
     */
    private long sequence = 0L;
    /**
     * 上一次时间戳
     */
    private long lastTimeStamp = -1L;

    /**
     * 构造单例模式
     * @param dataCenterId  数据中心ID
     * @param machineId  机器标识ID
     */
    public static IdWorkerSingleton getInstance(Long dataCenterId, long machineId) {
        if (null == instance) {
            synchronized (IdWorkerSingleton.class) {
                if (null == instance) {
                    instance = new IdWorkerSingleton(dataCenterId, machineId);
                }
            }
        }
        return instance;
    }

    /**
     * 获取IdWorkSingleton实例
     */
    public static IdWorkerSingleton getInstance() {
        return getInstance(0L,1);
    }

    /**
     * 构造方法，以数据中心id和机器标识（id）作为参数，范围为：0~2^5-1，即0~31
     */
    private IdWorkerSingleton(long dataCenterId, long machineId) {
        if (dataCenterId > MAX_DATACENTER_NUM || dataCenterId < 0) {
            throw new IllegalArgumentException("数据中心ID超出最大值或小于0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("机器标识超出最大值或小于0");
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    /**
     * 获取id
     */
    public synchronized long nextId() {
        long currentTimeStamp = getNewStamp();
        if (currentTimeStamp < lastTimeStamp) {
            throw new RuntimeException("时钟后移。停止生成ID");
        }
        if (currentTimeStamp == lastTimeStamp) {
            //这里表示当前调用和上次调用落在了相同的毫秒内，只能通过序列号自增来判断唯一，所以+1
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列已达到最大，只能等待下一个毫秒
            if (sequence == 0L) {
                currentTimeStamp = getNextMillis(lastTimeStamp);
            }
        } else {
            /*
             * 不同毫秒内，序列号置为0
             * 这里的前提为currentTimeStamp > lastTimeStamp，说明本次调用跟上次调用已不再同一毫秒内
             * 这是序号可以重置为0
             */
            sequence = 0L;
        }
        lastTimeStamp = currentTimeStamp;
        //时间戳部分
        return (currentTimeStamp - START_TIMESTAMP) << TIMESTAMP_LEFT
                //数据中心部分
                | dataCenterId << DATA_CENTER_LEFT
                //机器标识部分
                | machineId << MACHINE_LEFT
                //序列号部分
                | sequence;
    }

    /**
     * 得到下一个时间戳
     */
    private long getNextMillis(long lastTimeStamp) {
        long millis = getNewStamp();
        while (millis <= lastTimeStamp) {
            millis = getNewStamp();
        }
        return millis;
    }

    private long getNewStamp() {
        return System.currentTimeMillis();
    }

    /**
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {

        }
        long ts = 0;
        if (null != date) {
            date.getTime();
        }
        res = String.valueOf(ts);
        return res;
    }

    /**
     * 获取machineid
     */
    public static long getMachineId2(Long id) {
        return id >> MACHINE_LEFT & ~(-1L << MACHINE_BIT);
    }

    /**
     * 获取dataCenterId
     */
    public static long getDatacenterId2(Long id) {
        return id >> DATA_CENTER_LEFT & ~(-1L << DATACENTER_BIT);
    }

    /**
     * 获取machineId
     */
    public static long getMachineId(Long id) {
//        Long machineId = id >> 12L & ~(-1L << 5L);
//        Long dataCenterId = id >> 17L & ~(-1L << 5L);
        long workIdShift = id >>> MACHINE_LEFT;
        return workIdShift & getBitMask(MACHINE_BIT);
    }

    /**
     * 获取dataCenterId
     */
    public static long getDatacenterId(Long id) {
        long dataCenterIdShift = id >>> DATA_CENTER_LEFT;
        return dataCenterIdShift & getBitMask(DATACENTER_BIT);
    }

    /**
     * 获取sequence
     */
    public static long getSequence(Long id) {
        return id & getBitMask(SEQUENCE_BIT);
    }

    /**
     * 获取时间戳
     */
    public static long getTimeStamp(Long id) {
        long timeStampResult = id >>> TIMESTAMP_LEFT;
        return START_TIMESTAMP + timeStampResult;
    }

    /**
     * 获取掩码
     */
    private static long getBitMask(Long bit) {
        return ~(-1L << bit);
    }

    /**
     * 解析id并输出id包含的所有信息
     */
    public static JSONObject parseInfo(String id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        id = Long.toBinaryString(Long.parseLong(id));
        int len = id.length();
        jsonObject.put("binary", id);
        int sequenceStart = (int) (len < MACHINE_LEFT ? 0 : len - MACHINE_LEFT);
        int workerStart = (int) (len < DATA_CENTER_LEFT ? 0 : len - DATA_CENTER_LEFT);
        int timeStart = (int) (len < TIMESTAMP_LEFT ? 0 : len - TIMESTAMP_LEFT);
        String sequence = id.substring(sequenceStart, len);
        String machineId = sequenceStart == 0 ? "0" : id.substring(workerStart, sequenceStart);
        String dataCenterId = workerStart == 0 ? "0" : id.substring(timeStart, workerStart);
        String time = timeStart == 0 ? "0" : id.substring(0, timeStart);
        int sequenceInt = Integer.valueOf(sequence, 2);
        int machineIdInt = Integer.valueOf(machineId, 2);
        int dataCenterIdInt = Integer.valueOf(dataCenterId, 2);
        jsonObject.put("dataCenterId", dataCenterIdInt);
        jsonObject.put("machineId", machineIdInt);
        jsonObject.put("sequence", sequenceInt);
        long diffTime = Long.parseLong(time, 2);
        Long timeLong = diffTime + START_TIMESTAMP;
        jsonObject.put("time", stampToDate(timeLong.toString()));
        jsonObject.put("timestamp", timeLong);
        return jsonObject;
    }

    /**
     * 时间戳转日期时间
     */
    private static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
