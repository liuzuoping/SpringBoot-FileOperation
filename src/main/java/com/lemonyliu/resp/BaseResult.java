package com.lemonyliu.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResult {
    @ApiModelProperty(value = "提示信息")
    private String msg;
    @ApiModelProperty(value = "错误码（200为成功）",example ="200")
    private int code;
    @ApiModelProperty(value = "是否成功")
    public boolean isSuccess() {
        return code == ResponseCode.SUCCESS.getCode();
    }
}
