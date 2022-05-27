package com.lemonyliu.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.lemonyliu.resp.Result;
import com.lemonyliu.resp.UploadFileResponse;
import com.lemonyliu.service.OssService;
import com.lemonyliu.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2022-05-15
 */
@RestController
@RequestMapping("api/trms/report")
@Api(tags = "文件项目接口")
@ApiSort(1)
@Slf4j
public class ReportController {

    @Autowired
    ReportService reportService;

    @Autowired
    private OssService ossService;

    @ApiOperation(value = "1、上传文件",
            notes = "上传文件")
    @ApiOperationSupport(order = 1)
    @PostMapping(value = "uploadFile1")
    public Result<UploadFileResponse> uploadFile(HttpServletRequest request,
                                                 @RequestParam("file1") MultipartFile file) {
        UploadFileResponse data = reportService.uploadFile1(file);
        return Result.success(data);
    }

    @ApiOperation(value = "1、下载文件",
            notes = "下载文件")
    @ApiOperationSupport(order = 1)
    @GetMapping("downloadFile/{rid}")
    public ResponseEntity<Void> downloadFile(@PathVariable("rid") Long rid,
                                             HttpServletResponse response,
                                             HttpServletRequest request) throws IOException {

        this.ossService.downloadFile(rid, response);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "2、预览文件",
            notes = "预览文件")
    @ApiOperationSupport(order = 2)
    @GetMapping("preview/{rid}")
    public ResponseEntity<Void> preview(@PathVariable("rid") Long rid,
                                        HttpServletResponse response,
                                        HttpServletRequest request) {
        this.ossService.preview(rid, response);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "2、上传文件2",
            notes = "上传文件2")
    @ApiOperationSupport(order = 2)
    @PostMapping(value = "/uploadFile2")
    public Result<UploadFileResponse> uploadFile2(HttpServletRequest request,
                                                  @RequestParam("file2") MultipartFile file){
        UploadFileResponse data = reportService.uploadFile2(file);
        return Result.success(data);
    }

    @ApiOperation(value = "3、下载文件2",
            notes = "下载文件")
    @ApiOperationSupport(order = 3)
    @GetMapping("/downloadFile2")
    public ResponseEntity<Resource> downloadFile2(@RequestParam("fileName") String fileName, HttpServletRequest request) throws MalformedURLException {
        Resource resource = reportService.loadFileAsResource(fileName);
        String contentType = null;
        try{
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (IOException ex){
            log.info("Could not determine file type");
        }
        if (contentType == null){
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @ApiOperation(value = "4、预览文件2",
            notes = "预览文件")
    @ApiOperationSupport(order = 4)
    @GetMapping("preview2")
    public void preview2(@RequestParam("id") Long id,
                         HttpServletResponse response){
        reportService.preview(id,response);
    }
}

