package site.imcu.gossip.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：menghe
 * Created in 2019/8/17 9:53
 */
public interface FileUploadService {
    /**
     * 上传文件
     * @param sourcePath 文件
     * @return 文件名
     */
    String upload(String sourcePath);

    /**
     * 文件上传
     * @param multipartFile 文件
     * @return 文件路径
     */
    String upload(MultipartFile multipartFile);
}
