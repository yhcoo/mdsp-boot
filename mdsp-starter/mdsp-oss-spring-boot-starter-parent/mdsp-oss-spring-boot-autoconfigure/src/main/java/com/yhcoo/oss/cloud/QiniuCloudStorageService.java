package com.yhcoo.oss.cloud;

import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.yhcoo.oss.config.CloudStorageConfig;
import com.yhcoo.oss.exception.CheckedException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 七牛云存储
 * @author KingKey
 */
public class QiniuCloudStorageService extends CloudStorageService {
    private UploadManager uploadManager;

    private BucketManager bucketManager;
    private String token;

    public QiniuCloudStorageService(CloudStorageConfig config){
        this.config = config;

        //初始化
        init();
    }

    private void init(){
        uploadManager = new UploadManager(new Configuration(Zone.autoZone()));
        token = Auth.create(config.getAccessKey(), config.getSecretKey()).
                uploadToken(config.getBucketName());
    }

    @Override
    public String upload(byte[] data, String path) {
        try {
            Response res = uploadManager.put(data, path, token);
            if (!res.isOK()) {
                throw new RuntimeException("上传七牛出错：" + res.toString());
            }
        } catch (Exception e) {
            throw new CheckedException("上传文件失败，请核对七牛配置信息");
        }

        return config.getDomain() + "/" + path;
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            byte[] data = IOUtils.toByteArray(inputStream);
            return this.upload(data, path);
        } catch (IOException e) {
            throw new CheckedException("上传文件失败");
        }
    }

    @Override
    public String upload(String filePath,String path){

        try {
            Response res = uploadManager.put(filePath,path,token);
            if (res.isOK()) {
                throw new RuntimeException("上传七牛出错：" + res.toString());
            }
            return config.getDomain() + "/" + path;
        } catch (Exception e) {
            throw new CheckedException("上传文件失败，请核对七牛配置信息");
        }

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
