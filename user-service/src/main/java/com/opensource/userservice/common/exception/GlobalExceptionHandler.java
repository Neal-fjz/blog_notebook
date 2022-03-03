package com.opensource.userservice.common.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.opensource.userservice.common.resultful.CodeEnum;
import com.opensource.userservice.common.resultful.HttpJsonResponse;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.nio.file.AccessDeniedException;
import java.util.StringJoiner;

/**
 * 这是一个全局异常处理类，捕获全局异常
 * 异常可以分为三大类:
 * 1. 检查性异常: 最具代表的检查性异常是用户错误或问题引起的异常,例如搜索一个不存在的文件.
 * 2. 运行时异常: 运行时异常是可能被程序员避免的异常,与检查性异常相反,运行时异常可以在编译时被忽略
 * 3. 错误 ERROR: 错误不是异常,而是脱离程序员控制的问题,错误在代码中通常被忽略,例如,当栈溢出时,一个错误就产生了
 * <p>
 * 异常处理框架的思想
 * 1. JAVA 把异常当做对象来处理,并定义一个积累 java.lang.Throwable 作为所有异常地超类
 * 2. 在 JAVA API 中已经定义了许多的异常类,这些异常类分为两大类,错误 ERROR 和异常 Exception
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public HttpJsonResponse handleAccessDeniedException() {
        return HttpJsonResponse.errorWithEnum(CodeEnum.NO_TOKEN);
    }

    /**
     * 参数绑定错误
     */
    @ExceptionHandler(BindException.class)
    public HttpJsonResponse handleBindException(BindException ex) {
        StringJoiner sj = new StringJoiner(";");
        ex.getBindingResult().getFieldErrors().forEach(x -> sj.add(x.getDefaultMessage()));
        return HttpJsonResponse.errorWithMessage(460, sj.toString());
    }

    /**
     * 参数校验错误
     */
    @ExceptionHandler(ValidationException.class)
    public HttpJsonResponse handleValidationException(ValidationException ex) {
        return HttpJsonResponse.errorWithMessage(461, ex.getCause().getMessage());
    }

    /**
     * 字段校验不通过异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpJsonResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        StringJoiner sj = new StringJoiner(";");
        exception.getBindingResult().getFieldErrors().forEach(x -> sj.add(x.getDefaultMessage()));
        return HttpJsonResponse.errorWithMessage(407, sj.toString());
    }

    /**
     * Controller参数绑定错误
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public HttpJsonResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return HttpJsonResponse.errorWithMessage(501, ex.getMessage());
    }

    /**
     * 处理方法不支持异常
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public HttpJsonResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return HttpJsonResponse.errorWithEnum(CodeEnum.NOT_IMPLEMENTED);
    }

    /**
     * 自定义的异常
     */
    @ExceptionHandler(value = FailException.class)
    public HttpJsonResponse handleFailException(FailException e) {
        return HttpJsonResponse.errorWithMessage(409, e.getMessage());
    }

    /**
     * MyBatisSystemException 数据库操作异常
     */
    @ExceptionHandler(value = MyBatisSystemException.class)
    public HttpJsonResponse handleMyBatisSystemException(MyBatisSystemException e) {
        return HttpJsonResponse.errorWithMessage(409, e.getMessage());
    }

    /**
     * sa-token 的登录异常
     * @param e 获取异常
     * @return 返回统一格式
     */
    @ExceptionHandler(value = NotLoginException.class)
    public HttpJsonResponse notLoginException(NotLoginException e) {
        String message = "";
        if (e.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "未登录 请先登录";
        } else if (e.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "登录失效 请重新登录";
        } else if (e.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "登录已过期 请重新登录";
        } else if (e.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "当前账号已在其它地方登陆 请重新登录";
        } else if (e.getType().equals(NotLoginException.KICK_OUT)) {
            message = "当前登录已被退出";
        } else {
            message = "未登录 请先登录";
        }
        return HttpJsonResponse.errorWithMessage(401, message);
    }

    /**
     * 所有剩下的其它未知异常 - 兜底异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public HttpJsonResponse handleException(Exception ex) {
        return HttpJsonResponse.errorWithEnum(CodeEnum.INTERNAL_SERVER_ERROR);
    }

}
