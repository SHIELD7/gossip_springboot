package site.imcu.gossip.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import site.imcu.gossip.pojo.Gossip;
import site.imcu.gossip.pojo.GossipResult;
import site.imcu.gossip.service.FileUploadService;

/**
 * @author ：menghe
 * Created in 2019/9/5 20:23
 */
@Slf4j
@RestController
@CrossOrigin
@RequiresAuthentication
public class FileUploadController {
    private FileUploadService fileUploadService;

    @Autowired
    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/image")
    public GossipResult uploadImage(MultipartFile file){
        String filePath = fileUploadService.upload(file);
        if (filePath!=null){
            return GossipResult.success(filePath);
        }else {
            return GossipResult.error();
        }
    }
}
