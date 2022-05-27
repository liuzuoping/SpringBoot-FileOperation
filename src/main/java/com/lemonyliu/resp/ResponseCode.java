package com.lemonyliu.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode implements ResultCode{
    FAIL(0,"失败！"),
    SUCCESS(200,"成功"),
    ;
    private final int code;
    private final String msg;
}
