package com.example.auth.common;


/**
 * @author zj
 */


public enum ResultCode implements IResultCode {

    SUCCESS(200, "操作成功"),
    FAILURE(400, "业务异常"),
    UN_AUTHORIZED(401, "请求未授权"),
    CLIENT_UN_AUTHORIZED(401, "客户端请求未授权"),
    NOT_FOUND(404, "404 没找到请求"),
    MSG_NOT_READABLE(400, "消息不能读取"),
    METHOD_NOT_ALLOWED(405, "不支持当前请求方法"),
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    UNSUPPORTED_MEDIA_TYPE(415, "不支持当前媒体类型"),
    REQ_REJECT(403, "请求被拒绝"),
    INTERNAL_SERVER_ERROR(500, "服务器异常"),
    PARAM_MISS(400, "缺少必要的请求参数"),
    BAD_REQUEST(400, "请求异常"),
    PARAM_TYPE_ERROR(400, "请求参数类型错误"),
    PARAM_BIND_ERROR(400, "请求参数绑定错误"),
    PARAM_VALID_ERROR(400, "参数校验失败"),

    /**
     * 业务异常
     */
    USER_NOT_EXIST(18201, "用户不存在"),

    DEPT_EXIST(181401, "部门已存在"),
    DEPT_NOT_EXIST(181402, "部门不存在")
    ;

    final int code;
    final String message;

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    ResultCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }
}
