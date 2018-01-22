package com.viki.other.pattern.adapter;

public class TestAdapter {

    public static void main(String[] args) {

        GBSocketInterface gbSocket = new GBSocket();

        DBHotel hotel = new DBHotel();

        SocketAdapter socketAdapter = new SocketAdapter(gbSocket);

        hotel.setSocket(socketAdapter);

        hotel.charge();
    }
}