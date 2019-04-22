package com.yhcoo.oss.autoconfigure;

import com.yhcoo.oss.cloud.OSSFactory;
import com.yhcoo.oss.config.CloudStorageConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(OSSFactory.class)
public class OssAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(OSSFactory.class)
    public OSSFactory ossFactory() {
        return new OSSFactory();
    }

    @Bean
    @ConditionalOnMissingBean(name = "cloudStorageConfig")
    public CloudStorageConfig cloudStorageConfig() {
        return new CloudStorageConfig();
    }

}
