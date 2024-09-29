package com.example.auth.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Schema(description = "统一返回对象")
@Data
@NoArgsConstructor
public class ResultVO<T> implements Serializable {

    @Schema(description = "状态码")
    private Integer code;

    @Schema(description = "是否成功标识")
    private boolean success;

    @Schema(description = "响应数据")
    @JsonProperty("data")
    private T data;

    @Schema(description = "响应消息")
    private String msg;

    private ResultVO(IResultCode resultCode) {
        this(resultCode, null, resultCode.getMessage());
    }

    private ResultVO(IResultCode resultCode, String msg) {
        this(resultCode, null, msg);
    }

    private ResultVO(IResultCode resultCode, T data) {
        this(resultCode, data, resultCode.getMessage());
    }

    private ResultVO(IResultCode resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }

    private ResultVO(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.success = ResultCode.SUCCESS.code == code;
    }

    public static <T> ResultVO<T> data(T data) {
        return data(data, "操作成功");
    }

    public static <T> ResultVO<T> data(T data, String msg) {
        return data(200, data, msg);
    }

    public static <T> ResultVO<T> data(int code, T data, String msg) {
        return new ResultVO(code, data, Objects.isNull(data) ? "暂无承载数据" : msg);
    }

    public static <T> ResultVO<T> success(String msg) {
        return new ResultVO(ResultCode.SUCCESS, msg);
    }

    public static <T> ResultVO<T> success(IResultCode resultCode) {
        return new ResultVO(resultCode);
    }

    public static <T> ResultVO<T> fail(String msg) {
        return new ResultVO(ResultCode.FAILURE, msg);
    }

    public static <T> ResultVO<T> fail(int code, String msg) {
        return new ResultVO(code, (Object)null, msg);
    }

    public static <T> ResultVO<T> fail(IResultCode resultCode) {
        return new ResultVO<>(resultCode);
    }

    public static <T> ResultVO<T> fail(IResultCode resultCode, String msg) {
        return new ResultVO<>(resultCode, msg);
    }

    public static <T> ResultVO<T> status(boolean flag) {
        return flag ? success("操作成功") : fail("操作失败");
    }
}
