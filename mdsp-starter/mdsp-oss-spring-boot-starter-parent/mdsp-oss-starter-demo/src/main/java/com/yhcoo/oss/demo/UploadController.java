package com.yhcoo.oss.demo;

import com.yhcoo.oss.cloud.OSSFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author KingKey
 */
@Slf4j
@RestController
@Api(description = "文件上传接口")
@RequestMapping("/upload")
@Transactional
public class UploadController {

    @Autowired
    private OSSFactory ossFactory;

    @RequestMapping(value = "/file",method = RequestMethod.POST)
    @ApiOperation(value = "文件上传")
    public Object upload(@RequestParam("file") MultipartFile file,  HttpServletRequest request) {

        try {
            String[] videoSuffix = {"avi","wmv","mpeg","mp4","mov","mkv","flv","f4v","m4v","rmvb","rm","3gp","dat","ts","mts","vob"};
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

            boolean isContains = Arrays.asList(videoSuffix).contains(suffix);
            String ossPath = "file" + System.getProperty("file.separator") + renameFile( originalFilename );
            if(isContains){
                ossPath = "video" + System.getProperty("file.separator") + renameFile( originalFilename );
            }
            InputStream inputStream = file.getInputStream();
            String url = ossFactory.build().upload(inputStream,ossPath );
            return url;
        } catch (Exception e) {
            log.error(e.toString());
            return e.toString();
        }

    }



    /**
     * 以UUID重命名
     *
     * @param fileName
     * @return
     */
    public String renameFile(String fileName) {
        String extName = fileName.substring(fileName.lastIndexOf("."));
        return UUID.randomUUID().toString().replace("-", "") + extName;
    }

}
