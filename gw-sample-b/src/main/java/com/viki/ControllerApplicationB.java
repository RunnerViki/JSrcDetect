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
public class ControllerApplicationB {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ControllerApplicationB.class).web(true).run(args);
    }
}