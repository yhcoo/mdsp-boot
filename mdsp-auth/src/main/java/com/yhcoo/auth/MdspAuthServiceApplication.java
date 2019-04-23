package com.yhcoo.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.yhcoo.common", "com.yhcoo.auth"})
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class MdspAuthServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MdspAuthServiceApplication.class, args);
	}
}
