package com.ct.service.ForegroundService;

import com.ct.utils.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.Console;
import java.io.IOException;
import java.util.*;

@Service
public class FileuploadService {

    @Value("${fastdfs_ip}")
    private String fastdfs_ip;

    public List<String> imgUpload(MultipartFile[] files) {
        List<String> result = new ArrayList<>();//一个文件上传的结果
        for (MultipartFile file : files) {    //循环保存文件
            String fileType = file.getContentType();
            if (fileType.equals("image/jpeg") || fileType.equals("image/png") || fileType.equals("image/jpeg")) {
                if(file != null && file.getSize()>0){
                    String filename = file.getOriginalFilename();
                    String extName = filename.substring(filename.lastIndexOf(".")+1);
                    FastDFSClient client = new FastDFSClient("classpath:/fast_client.conf");
                    try {
                        String path = client.uploadFile(file.getBytes(), extName);
                        result.add(fastdfs_ip+path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }
}
