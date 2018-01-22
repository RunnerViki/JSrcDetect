package com.viki.core;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2018/1/22 11:26
 */
public interface Elastic {

    public void setAbstractNotifier();

    public void setAbstractMonitor();

    /** 监控并发送消息*/
    public void monitorAndNotify();
}
