package com.viki.org.apache.curator.framework.recipies.cache;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/11/14 9:26
 */
public class AliveNodeInfo {

    private String name;

    private String ip;

    public AliveNodeInfo(){}

    public AliveNodeInfo(String name, String ip) {
        this.name = name;
        this.ip = ip;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
