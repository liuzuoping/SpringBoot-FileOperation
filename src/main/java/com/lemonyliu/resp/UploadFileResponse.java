package com.lemonyliu.resp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadFileResponse {

    private String fileName;

    private String fileType;

    private String fileSize;

    private String realName;

}