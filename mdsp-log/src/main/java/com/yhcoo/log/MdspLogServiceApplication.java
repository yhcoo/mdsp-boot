package com.yhcoo.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = {"com.yhcoo.log", "com.yhcoo.common"})
public class MdspLogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MdspLogServiceApplication.class, args);
    }

}
