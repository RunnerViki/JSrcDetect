package com.viki.other;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by Viki on 2017/5/17.
 * Function: TODO
 */
public class Socket {

    public static void main(String[] args) {
        String url = "http://www.buyinball.com/v1/h5game2/Toubao/websock/723/rc40bloa/websocket?roomId=4&accessToken=412aa2c4872b2873b6d33ac123dfc1";
        url = "http://www.baidu.com";
        try {
            SocketChannel socketChannel = SocketChannel.open(/*new InetSocketAddress(url, 80)*/);
            socketChannel.bind(new InetSocketAddress(url, 80));
            ByteBuffer byteBuffer = ByteBuffer.allocate(10240);
            while(socketChannel.read(byteBuffer) > 0){
                byteBuffer.flip();
                System.out.println(new String(byteBuffer.array()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
