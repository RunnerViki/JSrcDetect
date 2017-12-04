package com.viki.utils;

import com.alibaba.fastjson.JSONObject;
import com.viki.transformer.TimeCostTransformer;

import java.lang.management.ManagementFactory;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/11/15 10:58
 */
public class TimeCostRecorder {

    private static final ConcurrentHashMap<String, TimeCostRecorderItem> records = new ConcurrentHashMap<>(100);

    // TODO 添加乐观锁
    public static void addRecord(String method, long elapse){
        if(records.get(method) == null){
            records.put(method, new TimeCostRecorderItem(method));
        }
        records.get(method).put(elapse);
    }

    public static String report(){
        return JSONObject.toJSONString(records);
    }

    public static void main(String[] args) {
        System.out.println(TimeCostTransformer.class.getResource("").getPath());
        System.out.println(ManagementFactory.getRuntimeMXBean().getClassPath());
    }
}
