package com.yhcoo.oss.config;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * 云存储配置信息
 * @author KingKey
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "mdsp.oss")
public class CloudStorageConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    //类型 1：七牛  2：阿里云  3：腾讯云
    @Range(min=1, max=3, message = "类型错误")
    private Integer type;

    //七牛绑定的域名,阿里云绑定的域名,腾讯云绑定的域名
    private String domain;

    //七牛路径前缀,阿里云路径前缀,腾讯云路径前缀
    private String pathPrefix;

    //七牛ACCESS_KEY,阿里云AccessKeyId,腾讯云SecretId
    private String accessKey;

    //七牛SECRET_KEY,阿里云AccessKeySecret,腾讯云SecretId
    private String secretKey;

    //七牛存储空间名,阿里云BucketName,腾讯云BucketName
    private String bucketName;

    //阿里云EndPoint (即上传地址：https://oss.aliyuncs.com )
    private String aliyunEndPoint;

    //腾讯云AppId
    private Integer qcloudAppId;
    //腾讯云COS所属地区
    private String qcloudRegion;

}
