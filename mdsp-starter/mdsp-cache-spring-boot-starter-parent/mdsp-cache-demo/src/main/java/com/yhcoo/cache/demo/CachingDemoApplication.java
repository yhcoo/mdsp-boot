package com.yhcoo.cache.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 *
 * @date 2018年1月29日 下午1:11:10
 * @version 1.0.0
 */
@EnableCaching
@SpringBootApplication
public class CachingDemoApplication {

	public static void main(String[] args) {
        SpringApplication.run(CachingDemoApplication.class, args);
    }
}
