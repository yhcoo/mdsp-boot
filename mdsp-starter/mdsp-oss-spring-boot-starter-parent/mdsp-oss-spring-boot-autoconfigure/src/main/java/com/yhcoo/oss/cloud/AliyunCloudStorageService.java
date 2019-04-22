package com.yhcoo.oss.cloud;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.yhcoo.oss.config.CloudStorageConfig;
import com.yhcoo.oss.exception.CheckedException;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

/**
 * 阿里云存储
 * @author KingKey
 */
public class AliyunCloudStorageService extends CloudStorageService {
    private OSSClient client;

    public AliyunCloudStorageService(CloudStorageConfig config){
        this.config = config;
        //初始化
        init();
    }

    private void init(){
        client = new OSSClient(config.getAliyunEndPoint(), config.getAccessKey(),
                config.getSecretKey());
    }

    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            client.putObject(config.getBucketName(), path, inputStream);
        } catch (Exception e){
            throw new CheckedException("上传文件失败，请检查配置信息");
        }

        return config.getDomain() + "/" + path;
    }


    @Override
    public String upload(String filePath,String path){
        try {
            client.putObject(new PutObjectRequest(config.getBucketName(), path, new File(filePath)));
            return config.getDomain() + "/" + path;
        } catch (OSSException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new CheckedException("上传文件失败");
        } finally {
            client.shutdown();
        }
        return "";
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(config.getPathPrefix(), suffix));
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix, String dir) {
        return upload(data, getPath(config.getPathPrefix(), suffix,dir));
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(config.getPathPrefix(), suffix));
    }
}
