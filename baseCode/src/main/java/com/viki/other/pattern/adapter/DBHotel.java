package com.viki.other.pattern.adapter;

/**
 * 德国宾馆
 */
public class DBHotel {

    //旅馆中有一个德标的插口
    private DBSocketInterface dbSocket;

    public DBHotel() {
    }

    public DBHotel(DBSocketInterface dbSocket) {
        this.dbSocket = dbSocket;
    }

    public void setSocket(DBSocketInterface dbSocket) {
        this.dbSocket = dbSocket;
    }

    //旅馆中有一个充电的功能
    public void charge() {

        //使用德标插口充电
        dbSocket.powerWithTwoRound();
    }
}