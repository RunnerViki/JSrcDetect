package com.viki.monitor;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2018/1/22 11:21
 */
public class JVMPerformanceMonitor extends AbstractMonitor<Long> {

    @Override
    public Long seek() {
//        Runtime.getRuntime().traceMethodCalls();
        long x = Runtime.getRuntime().totalMemory();
        System.out.println(x);
        return x;
    }
}
