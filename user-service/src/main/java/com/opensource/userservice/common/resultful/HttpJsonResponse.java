package com.opensource.userservice.common.resultful;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 统一个返回格式, 本同统一接口的设置是为了对接 ant design react 的前端数据格式
 * @author: 风君子
 * @date: 2021/10/25 16:13
 */

@Data
public class HttpJsonResponse implements Serializable {

    // 标识返回的错误状态码
    private Integer code;

    // 失败或错误返回的信息
    private String msg;

    // 是否成功
    private Boolean success;

    // 数据内容
    private Object data;

    // 信息处理方式,具体如下枚举
    private Integer showType;

    /**
     * 枚举的定义遵循如下
     * SILENT = 0,           // 不提示错误
     * WARN_MSG = 1,         // 警告信息提示
     * ERROR_MSG = 2,        // 错误信息提示
     * NOTIFICATION = 4,     // 通知提示
     * REDIRECT = 9,         // 页面跳转
     * SUCCESS = 3, // 操作成功通知
     */


    public HttpJsonResponse(Boolean success, Object data, Integer code, String msg, Integer showType) {
        this.code = code;
        this.msg = msg;
        this.success = success;
        this.data = data;
        this.showType = showType;
    }

    public HttpJsonResponse(Boolean success, Integer code, String msg, Integer showType) {
        this.code = code;
        this.msg = msg;
        this.success = success;
        this.showType = showType;
    }

    /**
     * 成功返回数据和总页数,主要用于表格数据查询
     */
    public static HttpJsonResponse successWithData(Object data) {
        return new HttpJsonResponse(true, data, CodeEnum.OK.getCode(), CodeEnum.OK.getMsg(), 0);
    }

    /**
     * 成功返回提示,定制化返回的信息
     */
    public static HttpJsonResponse successWithEnum(CodeEnum codeEnum) {
        return new HttpJsonResponse(true, codeEnum.getCode(), codeEnum.getMsg(), 3);
    }

    /**
     * 操作成功,返回提示,用于 增 /删 /改
     */
    public static HttpJsonResponse successNoData(String msg) {
        return new HttpJsonResponse(true, 200, msg, 3);
    }

    /**
     * 操作错误,用于不符合要求时返回提示
     */
    public static HttpJsonResponse errorWithEnum(CodeEnum httpStatusEnum) {
        return new HttpJsonResponse(true, httpStatusEnum.getCode(), httpStatusEnum.getMsg(), 2);
    }

    /**
     * 操作错误,用于定制化的信息反馈
     */
    public static HttpJsonResponse errorWithMessage(Integer code, String msg) {
        return new HttpJsonResponse(true, code, msg, 2);
    }

    /**
     * 操作错误,用于定制化的信息反馈
     */
    public static HttpJsonResponse errorWithMessageAndShowType(Integer code, String msg, Integer showType) {
        return new HttpJsonResponse(true, code, msg, showType);
    }
}
