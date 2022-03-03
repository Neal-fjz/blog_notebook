package com.opensource.userservice.common.exception;

import com.opensource.userservice.common.resultful.CodeEnum;
import lombok.Getter;

/**
 * @Description: 自定义错误 操作失败时抛出
 * @author: 风君子
 * @date: 2022/03/03 19:10
 */

@Getter
public class FailException extends RuntimeException {

    private final Integer code;

    public FailException(CodeEnum codeEnum) {
        super(codeEnum.getMsg());
        this.code = codeEnum.getCode();
    }

    public FailException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
