package com.ct.utils;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.IOException;

public class FastDFSClient {

    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageClient1 storageClient = null;
    private StorageServer storageServer = null;

    public FastDFSClient(String conf) {
        try {
            if (conf.contains("classpath:")) {
                conf=conf.replaceAll("classpath:", this.getClass().getResource("/").getPath());
            }
            ClientGlobal.init(conf);
            trackerClient = new TrackerClient();
            trackerServer=trackerClient.getConnection();
            storageClient=new StorageClient1(trackerServer,storageServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String uploadFile(String fileName, String extName, NameValuePair[] metas) {
        try {
            return storageClient.upload_file1(fileName, extName, metas);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String uploadFile(String fileName,String extName) {
        return this.uploadFile(fileName, extName, null);
    }
    public String uploadFile(byte[] content,String extName,NameValuePair[] metas) {
        try {
            return storageClient.upload_file1(content, extName, metas);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String uploadFile(byte[] content,String extName) {
        return this.uploadFile(content, extName, null);
    }

}