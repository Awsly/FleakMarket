package com.ct.controller.ForegroundController;

import com.ct.service.ForegroundService.FileuploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件上传接口
 */
@RestController
@RequestMapping(value = "/fileUpload")
public class FileuploadController {

    @Autowired
    private FileuploadService fileuploadservice;

    /**
     * 图片上传接口
     * @return String
     */
    @RequestMapping(value = "/imgUpload" ,method = RequestMethod.POST)
    public List<String> imgUpload(@RequestParam("file") MultipartFile[] file) throws Exception{
        return fileuploadservice.imgUpload(file);
    }
}
