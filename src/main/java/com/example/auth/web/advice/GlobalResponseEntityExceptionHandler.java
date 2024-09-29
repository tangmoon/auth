package com.example.auth.web.advice;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.exception.SaTokenException;
import com.example.auth.common.ResultCode;
import com.example.auth.common.ResultVO;
import com.example.auth.exception.AdminServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class GlobalResponseEntityExceptionHandler {


    @ExceptionHandler(NotLoginException.class)
    public ResponseEntity<Object> handlerNotLoginException(NotLoginException ex){
        log.error("NotLoginException: {}", ex.getMessage());
        ResultVO resultVO = ResultVO.fail(401, ex.getMessage());
        return new ResponseEntity<>(resultVO, HttpStatus.OK);
    }

    @ExceptionHandler(NotRoleException.class)
    public ResponseEntity<Object> handlerNotRoleException(NotRoleException ex){
        log.error("NotRoleException: {}", ex.getMessage());
        ResultVO resultVO = ResultVO.fail(403, ex.getMessage());
        return new ResponseEntity<>(resultVO, HttpStatus.OK);
    }

    @ExceptionHandler(NotPermissionException.class)
    public ResponseEntity<Object> handlerNotPermissionException(NotPermissionException ex){
        log.error("NotPermissionException: {}", ex.getMessage());
        ResultVO resultVO = ResultVO.fail(403, ex.getMessage());
        return new ResponseEntity<>(resultVO, HttpStatus.OK);
    }

    @ExceptionHandler(SaTokenException.class)
    public ResponseEntity<Object> handlerSaTokenException(SaTokenException ex) {
        log.error("SaTokenException: {}", ex.getMessage());
        ResultVO resultVO = ResultVO.fail(401, ex.getMessage());
        return new ResponseEntity<>(resultVO, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AdminServiceException.class)
    public ResponseEntity<Object> handlerAdminServiceException(AdminServiceException ex) {
        log.error("AdminServiceException: {}", ex.getMessage());
        ResultVO resultVO = ResultVO.fail(ex.getResultCode(), ex.getMessage());
        return new ResponseEntity<>(resultVO, HttpStatus.OK);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handlerBindException(BindException ex) {
        log.error("BindException: {}", ex.getMessage());
        ResultVO resultVO = ResultVO.fail(ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(resultVO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerBindException(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException: {}", ex.getMessage());
        ResultVO resultVO = ResultVO.fail(ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(resultVO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.error("HttpRequestMethodNotSupportedException: {}", ex.getMessage());
        ResultVO resultVO = ResultVO.fail(ResultCode.METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(resultVO, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handlerNoHandlerFoundException(NoHandlerFoundException ex) {
        log.error("NoHandlerFoundException: {}", ex.getMessage());
        ResultVO resultVO = ResultVO.fail(ResultCode.NOT_FOUND);
        return new ResponseEntity<>(resultVO, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex) {
        log.error("Exception: {}", ex.getMessage());
        ResultVO resultVO = ResultVO.fail(ResultCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(resultVO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
