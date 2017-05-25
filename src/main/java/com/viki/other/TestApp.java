package com.viki.other;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Viki on 2017/5/17.
 * Function: TODO
 */

public class TestApp {



    public static void main(String[] args) {
        int i = 0;
        try {
            // open websocket
            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("ws://www.buyinball.com/v1/h5game2/Toubao/websock/723/rc40bloa/websocket?roomId=4&accessToken=412aa2c4872b2873b6d33ac123dfc1"));
            // add listener
            clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
                int i = 0;
                public void handleMessage(String message) {
                    System.out.println(message);
                }
            });

            // send message to websocket
            /*while(true){
                Thread.sleep(5000);
                clientEndPoint.sendMessage("{type:ping}");
            }*/
            if(i == 0){
                clientEndPoint.sendMessage("{type:ping}");
//                clientEndPoint.sendMessage("{type:enter,body:{accessToken:412aa2c4872b2873b6d33ac123dfc1,roomId:4}}");
            }else{
                clientEndPoint.sendMessage("{type:ping}");
            }
            i++;


            // wait 5 seconds for messages from websocket
            Thread.sleep(5000);

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }
}