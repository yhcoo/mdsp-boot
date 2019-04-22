package com.yhcoo.tsc.msg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class MdspTscMsgApplication {

	public static void main(String[] args) {
		SpringApplication.run(MdspTscMsgApplication.class, args);
	}

}
