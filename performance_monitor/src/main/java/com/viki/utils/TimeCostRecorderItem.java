package com.viki.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/11/15 10:57
 */
public class TimeCostRecorderItem{
    String methodName;

    TreeMap<Long, Integer> details = new TreeMap<Long,Integer>(new Comparator<Long>() {
        @Override
        public int compare(Long o1, Long o2) {
            long diff = o2 - o1;
            return diff > Integer.MAX_VALUE ? 1 : (diff < Integer.MIN_VALUE ? -1 : (int)diff);
        }
    });

    Long totalElapse = 0L;

    public TimeCostRecorderItem(String methodName){
        this.methodName = methodName;
    }

    public void put(Long elapse){
        if(details.containsKey(elapse)){
            details.put(elapse, details.get(elapse) + 1);
        }else{
            details.put(elapse, 1);
        }
        totalElapse += elapse;
    }

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }

    public String getMethodName() {
        return methodName;
    }


    public TreeMap<Long, Integer> getDetails() {
        return details;
    }


    public Long getTotalElapse() {
        return totalElapse;
    }

}