package com.lemonyliu.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="统一结果对象", description="")
public class Result<T> extends BaseResult{
    private final static Result<?> EMPTY = new Result<>();
    @ApiModelProperty(value = "响应数据对象")
    private T data;

    private Result() {
        this.data = null;
    }
    private Result(T data, int code) {
        super(null,code);
        this.data = data;
    }
    private Result(String msg, int code) {
        super(msg,code);
    }

    /**
     *
     * 功能描述: 创建一个空Result类
     */
    public static <T> Result<T> empty() {
        @SuppressWarnings("unchecked")
        Result<T> t = (Result<T>) EMPTY;
        return t;
    }

    /**
     *
     * 功能描述: 生成一个成功状态Result类
     * @param: data
     * @return: Result<T>
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data, ResponseCode.SUCCESS.getCode());
        result.setMsg(ResponseCode.SUCCESS.getMsg());
        return result;
    }

    public static <T> Result<T> success() {
        return new Result<>(null, ResponseCode.SUCCESS.getCode());
    }

    public static <T> Result<T> fail(String message) {
        return (Result<T>)new Result<>(message, ResponseCode.FAIL.getCode());
    }

    public static <T> Result<T> fail(ResultCode resultCode) {
        return (Result<T>)new Result<>(resultCode.getMsg(), resultCode.getCode());
    }

    public static <T> Result<T> fail(int code,String message) {
        return (Result<T>)new Result<>(message, code);
    }

    /**
     *
     * 功能描述: 判断是否传入值是否为空,非空则返回值，为空则返回失败信息
     * @param: message 返回的错误信息
     * @return: Result<T>
     */
    public <T> Result<T> orFail(String message) {
        if (null != data) {
            return (Result<T>) this;
        } else {
            super.setMsg(getMsg());
            super.setCode(ResponseCode.FAIL.getCode());
        }
        return (Result<T>) this;
    }
}