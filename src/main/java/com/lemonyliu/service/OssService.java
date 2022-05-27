package com.lemonyliu.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * oss 业务接口
 *
 */
public interface OssService {

    String uploadFile (MultipartFile file, String fileName);

    void preview (Long rid, HttpServletResponse response);

    void downloadFile (Long rid, HttpServletResponse response) throws IOException;

    void deleteOneFile (String objectName);

    void deleteFileList (List<String> keys);
}