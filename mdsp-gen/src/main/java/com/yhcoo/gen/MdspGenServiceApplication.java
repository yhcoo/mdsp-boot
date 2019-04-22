package com.yhcoo.gen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.yhcoo.gen", "com.yhcoo.common"})
public class MdspGenServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MdspGenServiceApplication.class, args);
    }

}
