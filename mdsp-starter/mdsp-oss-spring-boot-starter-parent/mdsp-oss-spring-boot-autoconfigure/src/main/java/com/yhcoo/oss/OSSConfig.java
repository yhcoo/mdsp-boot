package com.yhcoo.oss;

import com.yhcoo.oss.config.CloudStorageConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *@author LCN on 2017/7/5.
 */

@Configuration
//@ComponentScan("com.yhcoo.saas.common.oss")
public class OSSConfig {

//	@Autowired
//	private CloudStorageConfig cloudStorageConfig;

//	@Bean
//	public OSSFactory getOSSFactory() {
//		OSSFactory ossFactory = new OSSFactory();
//		return ossFactory;
//	}

	@Bean
	public CloudStorageConfig getCloudStorageConfig() {
		CloudStorageConfig cloudStorageConfig = new CloudStorageConfig();
		return cloudStorageConfig;
	}


}
