package com.lemonyliu.resp;

import java.io.Serializable;

public interface ResultCode extends Serializable {

    /**
     * 获取消息
     */
    String getMsg();

    /**
     * 获取状态码
     */
    int getCode();

}
