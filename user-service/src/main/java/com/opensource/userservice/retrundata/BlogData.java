package com.opensource.userservice.retrundata;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
@Component
public class BlogData extends LinkedHashMap {

    public BlogData()
    {

    }

    public BlogData(int code,String error,Object data)
    {
        this.setdata(data);
        this.setcode(code);
        this.seterror(error);
    }

    public BlogData(int code,Object data)
    {
        this.setdata(data);
        this.setcode(code);

    }


    // 本次返回的状态码
    public BlogData setcode(int code) {
        this.put("code", code);
        return this;
    }

    //错误信息
    public BlogData seterror(String error) {
        this.put("error", error);
        return this;
    }

    //返回数据
    public BlogData setdata(Object data) {
        this.put("data", data);return this;
    }

}
