package com.lemonyliu.service.impl;

import com.lemonyliu.constant.CommonConstant;
import com.lemonyliu.entity.Report;
import com.lemonyliu.exception.FileStorageException;
import com.lemonyliu.mapper.ReportMapper;
import com.lemonyliu.resp.UploadFileResponse;
import com.lemonyliu.service.OssService;
import com.lemonyliu.service.ReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemonyliu.util.FileUtil;
import com.lemonyliu.util.HttpUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-05-15
 */
@Service
@Slf4j
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {
    @Autowired
    ReportMapper reportMapper;
    @Autowired
    private OssService ossService;
    @Value("${file.path}")
    private String fileStorageLocation;

    @Override
    public UploadFileResponse uploadFile1(MultipartFile file) {
        String fileName = org.springframework.util.StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        if (fileName.contains("..")) {
            log.error("File name contains invalid path sequence");
        }
        String realName = this.ossService.uploadFile(file, fileName);
        Report report = new Report();
        report.setUid("zuoping.liu");
        report.setVersionLock(0);
        report.setTitle(fileName);
        report.setPath(realName);
        report.setSize(FileUtil.getFileSizeDesc(file.getSize()));
        LocalDateTime localDateTime = LocalDateTime.now();
        report.setCreateTime(localDateTime);
        report.setLastUpdateTime(localDateTime);
        report.setDelFlag(false);
        report.setCid0(191L);
        // report表新增
        int result = reportMapper.insert(report);
        if (result <= 0) {
            log.error("新增报告失败, sql返回结果: {}", result);
            return null;
        }
        return new UploadFileResponse(fileName, file.getContentType(), FileUtil.getFileSizeDesc(file.getSize()), realName);
    }

    @Override
    public UploadFileResponse uploadFile2(MultipartFile file) {
        Report report = new Report();
        report.setUid("zuoping.liu");
        report.setVersionLock(0);

        // 处理展示的重复文件名
        List<String> titleList = reportMapper.selectTitleList("zuoping.liu");
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String realName = storeFile(file);
        String suffix = FileUtil.getFileSuffix(filename);
        StringBuilder sb = new StringBuilder();
        if (!org.apache.commons.lang3.StringUtils.isBlank(filename) && titleList.contains(filename)){
            List<String> filter = titleList.stream().filter(title -> title.contains(filename)
                    || title.contains(filename.substring(0, filename.lastIndexOf(".")) + "(")).collect(Collectors.toList());
            sb.append(filename, 0, filename.lastIndexOf("."))
                    .append("(")
                    .append(filter.size())
                    .append(")")
                    .append(FileUtil.getFileSuffix(suffix));
        }else {
            sb.append(filename);
        }
        report.setTitle(sb.toString());
        report.setPath(realName);
        report.setSize(FileUtil.getFileSizeDesc(file.getSize()));
        LocalDateTime localDateTime = LocalDateTime.now();
        report.setCreateTime(localDateTime);
        report.setLastUpdateTime(localDateTime);
        report.setDelFlag(false);
        report.setCid0(191L);
        // report表新增
        int result = reportMapper.insert(report);
        if (result <= 0) {
            log.error("新增报告失败, sql返回结果: {}", result);
            return null;
        }
        return new UploadFileResponse(filename,file.getContentType(),FileUtil.getFileSizeDesc(file.getSize()), realName);
    }

    @Override
    public void preview(Long id, HttpServletResponse response) {
        String path = fileStorageLocation+"/"+reportMapper.selectReportPathById(id);
        if (Objects.isNull(path)) {
            throw new FileStorageException("文件不存在");
        }
        preview(new File(path),id, response);
    }
    /**
     * 文件预览
     */
    private void preview(File file, Long id, HttpServletResponse response) {
        addCommonResponseHeader(id,response);
        writeFile2Response(file, response);
    }
    /**
     * 添加公用的响应头
     */
    private void addCommonResponseHeader(Long id,HttpServletResponse response) {
        response.reset();
        HttpUtil.addCorsResponseHeader(response);
        String title = reportMapper.selectReportTitleById(id);
        if(title.substring(title.length()-4,title.length()).equals(".pdf")){
            response.setHeader(CommonConstant.CONTENT_TYPE_STR, "application/pdf");
            response.setContentType("application/pdf");
        }else if(title.substring(title.length()-4,title.length()).equals(".doc")){
            response.setHeader(CommonConstant.CONTENT_TYPE_STR, "application/msword");
            response.setContentType("application/msword");
        }else{
            response.setHeader(CommonConstant.CONTENT_TYPE_STR, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        }
    }
    /**
     * 单位长度
     */
    private static final Integer UNIT_INT = 1024;
    /**
     * 文件写入响应实体
     */
    private void writeFile2Response(File file, HttpServletResponse response) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buffer = new byte[UNIT_INT];
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != CommonConstant.MINUS_ONE_INT) {
                os.write(buffer, CommonConstant.ZERO_INT, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            //log.error("文件写入响应实体失败", e);
        }
    }

    @Override
    public String storeFile(MultipartFile file) {
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String suffix = FileUtil.getFileSuffix(filename);
        // 处理展示的重复文件名
        List<String> titleList = reportMapper.selectTitleList("zuoping.liu");
        StringBuilder sb = new StringBuilder();
        if (!org.apache.commons.lang3.StringUtils.isBlank(filename) && titleList.contains(filename)){
            List<String> filter = titleList.stream().filter(title -> title.contains(filename)
                    || title.contains(filename.substring(0, filename.lastIndexOf(".")) + "(")).collect(Collectors.toList());
            sb.append(filename, 0, filename.lastIndexOf("."))
                    .append("(")
                    .append(filter.size())
                    .append(")")
                    .append(FileUtil.getFileSuffix(suffix));
        }else {
            sb.append(filename);
        }
        try {
            if (filename.contains("..")) {
                throw new FileStorageException("File name contains invalid path sequence");
            }
            String realName = sb.toString();
            String targetLocation = this.fileStorageLocation + "/" + realName;
            File dest = new File(targetLocation);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
            return realName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + filename + ". Please try again later!", ex);
        }
    }

    @Override
    public Resource loadFileAsResource(String fileName) throws MalformedURLException {
        Path path = Paths.get(fileStorageLocation);
        Path targetPath = path.resolve(fileName).normalize();
        Resource resource = new UrlResource(targetPath.toUri());
        return resource;
    }
}
