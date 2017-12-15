package com.viki; /**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/12/14 14:40
 */
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ControllerApplicationA {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ControllerApplicationA.class).web(true).run(args);
    }
}