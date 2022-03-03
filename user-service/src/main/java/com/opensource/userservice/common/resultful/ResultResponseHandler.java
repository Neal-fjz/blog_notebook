package com.opensource.userservice.common.resultful;

import cn.hutool.json.JSONUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Description: 全局结果封装,对于 controller 业务层返回的数据进行再次包装,按照预定格式返回给前端.
 * @author: 风君子
 * 2022/03/03 18:29
 */
@ControllerAdvice(basePackages = "com.erp.system")
public class ResultResponseHandler implements ResponseBodyAdvice<Object> {

    // 是否支持 advice 功能,支持: true, 不支持: false
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        // 如果返回的是 string 会默认调用 String 的处理器直接返回,所以对 string 要特别处理.
        //必须在方法体上标注RequestMapping(produces = "application/json; charset=UTF-8")
        if (body instanceof String) {
            String str = JSONUtil.toJsonStr(HttpJsonResponse.successWithData(body));
            return str;
        }
        if (body instanceof HttpJsonResponse) {
            return body;
        }
        if (body instanceof CodeEnum) {
            return HttpJsonResponse.successWithEnum((CodeEnum) body);
        }
        return HttpJsonResponse.successWithData(body);
    }
}
