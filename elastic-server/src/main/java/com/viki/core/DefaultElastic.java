package com.viki.core;

import com.viki.monitor.AbstractMonitor;
import com.viki.monitor.JVMPerformanceMonitor;
import com.viki.notifier.AbstractNotifier;
import com.viki.notifier.ZKNotifier;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2018/1/22 11:30
 */
public class DefaultElastic implements Elastic {

    AbstractNotifier abstractNotifier;

    AbstractMonitor abstractMonitor;

    @Override
    public void setAbstractNotifier() {
        abstractNotifier = new ZKNotifier();
    }

    @Override
    public void setAbstractMonitor() {
        abstractMonitor =  new JVMPerformanceMonitor();
    }

    @Override
    public void monitorAndNotify() {
        Object seek = abstractMonitor.seek();
        Long mem = (Long)seek;
        if(mem > 10){
            abstractNotifier.notify();
        }
    }
}
