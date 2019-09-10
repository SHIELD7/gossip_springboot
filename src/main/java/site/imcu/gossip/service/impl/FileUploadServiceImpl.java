package site.imcu.gossip.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import com.aliyun.oss.OSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import site.imcu.gossip.config.AliyunConfig;
import site.imcu.gossip.service.FileUploadService;

import java.io.File;
import java.io.IOException;

/**
 * @author ：menghe
 * Created in 2019/8/20 17:00
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {
    private OSS ossClient;
    private AliyunConfig aliyunConfig;

    @Autowired
    public FileUploadServiceImpl(OSS ossClient, AliyunConfig aliyunConfig) {
        this.ossClient = ossClient;
        this.aliyunConfig = aliyunConfig;
    }

    @Override
    public String upload(String sourcePath) {
        String filePath = getFilePath();
        ossClient.putObject(aliyunConfig.getBucketName(),filePath,new File(sourcePath));
        return filePath;
    }

    @Override
    public String upload(MultipartFile multipartFile) {
        String filePath = getFilePath();
        try {
            String picPath = "e:\\imgUpload\\";
            if(!isChartPathExist(picPath)){
                picPath = "/home/imgUpload/";
            }
            File file = new File(picPath+multipartFile.getName());
            FileUtil.writeFromStream(multipartFile.getInputStream(),file);
            ossClient.putObject(aliyunConfig.getBucketName(),filePath,file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return filePath;
    }

    private String getFilePath() {
        DateTime dateTime = new DateTime();
        return "images/" + dateTime.toString("yyyy")
                + "/" + dateTime.toString("MM") + "/"
                + dateTime.toString("dd") + "/" + System.currentTimeMillis() +
                RandomUtil.randomNumbers(4) + ".png";
    }

    private static boolean isChartPathExist(String dirPath) {
        File file = new File(dirPath);
        return file.exists();
    }
}
