package com.lemonyliu.service;

import com.lemonyliu.entity.Report;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lemonyliu.resp.UploadFileResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-05-15
 */
public interface ReportService extends IService<Report> {
    UploadFileResponse uploadFile1(MultipartFile file);

    void preview(Long id, HttpServletResponse response);

    String storeFile(MultipartFile file);

    Resource loadFileAsResource(String fileName) throws MalformedURLException;

    UploadFileResponse uploadFile2(MultipartFile file);
}
