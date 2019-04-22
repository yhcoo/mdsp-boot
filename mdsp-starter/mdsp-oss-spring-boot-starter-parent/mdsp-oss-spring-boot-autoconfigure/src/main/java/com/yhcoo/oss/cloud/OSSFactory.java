package com.yhcoo.oss.cloud;

import com.yhcoo.oss.config.CloudStorageConfig;
import com.yhcoo.oss.constants.OSSConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 文件上传Factory
 *
 * @author KingKey
 */
@Component
public class OSSFactory {

    @Autowired
    private CloudStorageConfig cloudStorageConfig;

    public CloudStorageService build() {
        if (cloudStorageConfig.getType().equals(OSSConstant.TYPE_QINIU)) {
            return new QiniuCloudStorageService(cloudStorageConfig);
        } else if (cloudStorageConfig.getType().equals(OSSConstant.TYPE_ALIYUN)) {
            return new AliyunCloudStorageService(cloudStorageConfig);
        } else if (cloudStorageConfig.getType().equals(OSSConstant.TYPE_QCLOUD)) {
            return new QcloudCloudStorageService(cloudStorageConfig);
        }
        return null;
    }

}
