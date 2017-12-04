package com.viki.premain;

import com.viki.transformer.TimeCostTransformer;
import com.viki.utils.TimeCostRecorder;

import java.lang.instrument.Instrumentation;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/11/6 17:34
 */
public class Premain {

    public static void premain(String options, Instrumentation ins) {
        HashMap<String,String> optionMap = getOptions(options);
        String timecostMonitorPackage = optionMap.get("timecost_monitor_package");
        timecostMonitorPackage = timecostMonitorPackage == null ? System.getProperty("timecost_monitor_package"): timecostMonitorPackage;
        String timecostMonitorThreshold = optionMap.get("timecost_monitor_threshold");
        timecostMonitorThreshold = timecostMonitorThreshold == null ? System.getProperty("timecost_monitor_threshold"): timecostMonitorThreshold;
        timecostMonitorThreshold = timecostMonitorThreshold == null ? "100" : timecostMonitorThreshold;
        if(timecostMonitorPackage != null){
            ins.addTransformer(new TimeCostTransformer(timecostMonitorPackage, Long.parseLong(timecostMonitorThreshold)));
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
            scheduledThreadPoolExecutor.scheduleAtFixedRate(
                ()-> {
                    Thread.currentThread().setName("TimeCostReport");
                    System.out.println(new Date()+"\t【TimeCostReport】\t\t"+TimeCostRecorder.report());
                }, 1,30, TimeUnit.MINUTES);
        }
    }

    private static HashMap<String,String> getOptions(String optionsStr){
        HashMap<String,String> map;
        if(optionsStr == null || optionsStr.isEmpty()){
            return new HashMap<>(1);
        }
        String[] args = optionsStr.split(",");
        map = new HashMap<>(args.length);
        Arrays.stream(args)
                .filter(arg -> (arg.split(":")).length == 2)
                .forEach(arg -> {
            String[] argItem = arg.split(":");
            map.put(argItem[0], argItem[1]);
        });
        return map;
    }
}
