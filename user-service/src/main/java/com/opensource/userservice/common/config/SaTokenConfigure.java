package com.opensource.userservice.common.config;

import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.id.SaIdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: Sa-Token 权限认证 配置类
 * @author: 风君子
 * @date:  2022/03/03 18:29
 */

public class SaTokenConfigure implements WebMvcConfigurer {
    // 注册 Sa-Token 全局过滤器
    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                .addInclude("/**")
                .addExclude("/favicon.ico")
                .setAuth(obj -> {
                    // 校验 Id-Token 身份凭证
                    SaIdUtil.checkCurrentRequestToken();
                })
                .setError(e -> {
                    // 认证失败处理
                    System.out.println("认证失败");
                    return "认证失败";
                });
    }
}
