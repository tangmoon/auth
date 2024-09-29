package com.example.auth.exception;


import com.example.auth.common.IResultCode;
import com.example.auth.common.ResultCode;

/**
 * @author zj
 */
public class AdminServiceException extends RuntimeException {

    private final IResultCode resultCode;

    public AdminServiceException(String message) {
        super(message);
        this.resultCode = ResultCode.FAILURE;
    }

    public AdminServiceException(IResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public AdminServiceException(IResultCode resultCode, Throwable cause) {
        super(cause);
        this.resultCode = resultCode;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    public Throwable doFillInStackTrace() {
        return super.fillInStackTrace();
    }

    public IResultCode getResultCode() {
        return this.resultCode;
    }
}
