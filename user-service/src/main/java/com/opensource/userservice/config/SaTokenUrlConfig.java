package com.opensource.userservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

//注释处理
@Data
@Configuration
@ConfigurationProperties(prefix = "sa-token", ignoreUnknownFields = true)
public class SaTokenUrlConfig {

    /**
     * 拦截url
     */
    private List<String> interceptUrlList;

    /**
     * 开放url
     */
    private List<String> openUrlList;

    public List<String> getInterceptUrlList() {
        return interceptUrlList;
    }

    public void setInterceptUrlList(List<String> interceptUrlList) {
        this.interceptUrlList = interceptUrlList;
    }

    public List<String> getOpenUrlList() {
        return openUrlList;
    }

    public void setOpenUrlList(List<String> openUrlList) {
        this.openUrlList = openUrlList;
    }

}