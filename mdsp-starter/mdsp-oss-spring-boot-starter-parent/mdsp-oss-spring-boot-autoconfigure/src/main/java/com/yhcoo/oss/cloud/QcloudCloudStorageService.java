package com.yhcoo.oss.cloud;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;
import com.yhcoo.oss.config.CloudStorageConfig;
import com.yhcoo.oss.exception.CheckedException;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 腾讯云存储
 * @author KingKey
 */
public class QcloudCloudStorageService extends CloudStorageService {
    private COSClient client;

    public QcloudCloudStorageService(CloudStorageConfig config){
        this.config = config;

        //初始化
        init();
    }

    private void init(){
    	Credentials credentials = new Credentials(config.getQcloudAppId(), config.getAccessKey(),
                config.getSecretKey());
    	
    	//初始化客户端配置
        ClientConfig clientConfig = new ClientConfig();
        //设置bucket所在的区域，华南：gz 华北：tj 华东：sh
        clientConfig.setRegion(config.getQcloudRegion());
        
    	client = new COSClient(clientConfig, credentials);
    }

    @Override
    public String upload(byte[] data, String path) {
        //腾讯云必需要以"/"开头
        if(!path.startsWith("/")) {
            path = "/" + path;
        }
        
        //上传到腾讯云
        UploadFileRequest request = new UploadFileRequest(config.getBucketName(), path, data);
        String response = client.uploadFile(request);
        JSONObject jsonObject = JSONObject.fromObject(response);
        if(jsonObject.getInt("code") != 0) {
            throw new CheckedException("文件上传失败，" + jsonObject.getString("message"));
        }

        return config.getDomain() + path;
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
            UploadFileRequest request = new UploadFileRequest(config.getBucketName(), path, filePath);
            String response = client.uploadFile(request);
            JSONObject jsonObject = JSONObject.fromObject(response);
            if(jsonObject.getInt("code") != 0) {
                throw new CheckedException("文件上传失败，" + jsonObject.getString("message"));
            }
            return config.getDomain() + "/" + path;
        } catch (Exception e) {
            throw new CheckedException("上传文件失败");
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
