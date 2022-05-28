package com.lemonyliu.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.OSSObject;
import com.lemonyliu.entity.Report;
import com.lemonyliu.mapper.ReportMapper;
import com.lemonyliu.service.OssService;
import com.lemonyliu.util.HttpUtil;
import com.lemonyliu.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OssServiceImpl implements OssService {
    @Autowired
    ReportMapper reportMapper;

    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${oss.bucketName}")
    private String bucketName;
    @Value("${oss.baseFolder}")
    private String baseFolder;

    @Override
    public String uploadFile (MultipartFile file, String fileName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        InputStream inputStream = null;
        String objectName = "";
        try {
            inputStream = file.getInputStream();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            String date = simpleDateFormat.format(new Date());
            objectName = this.baseFolder + "/" + date + "/" + UUIDUtil.getUUID() + "_" + fileName;
            ossClient.putObject(bucketName, objectName, inputStream);
        } catch (Exception e) {
            log.error("上传文件失败", e);
        }
        ossClient.shutdown();
        return objectName;
    }

    @Override
    public void downloadFile (Long rid, HttpServletResponse response) throws IOException {
        Report report = this.reportMapper.selectById(rid);
        String objectName = report.getPath();
        String title = report.getTitle();
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
        BufferedInputStream bis = new BufferedInputStream(ossObject.getObjectContent());
        response.setHeader("Content-Disposition",
                "attachment;filename="
                        + new String(title.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
        response.setHeader("Content-Type",
                "application/octet-stream");
        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
        this.write(bis, bos);
        ossClient.shutdown();
    }

    @Override
    public void preview (Long rid, HttpServletResponse response) {
        Report report = this.reportMapper.selectById(rid);
        String objectName = report.getPath();
        String title = report.getTitle();
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
        InputStream fileStream = ossObject.getObjectContent();
        HttpUtil.addCommonResponseHeader(title, response);
        try {
            BufferedInputStream bis = new BufferedInputStream(fileStream);
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            this.write(bis, bos);
        } catch (Exception e) {
            log.error("预览文件失败", e);
        }
        ossClient.shutdown();
    }

    @Override
    public void deleteOneFile (String objectName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.deleteObject(bucketName, objectName);
        ossClient.shutdown();
    }

    @Override
    public void deleteFileList (List<String> keys) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
        ossClient.shutdown();
    }

    private void write(BufferedInputStream bis, BufferedOutputStream bos) throws IOException {
        int len;
        byte[] b = new byte[1024];
        while ((len = bis.read(b)) != -1) {
            bos.write(b, 0, len);
        }
        bos.close();
        bis.close();
    }
}