package com.opensource.userservice.controller;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.stp.StpUtil;
import com.opensource.userservice.retrundata.BlogData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/User")
public class UserController {

    @GetMapping("time")
    public String time() {
        log.info("time: {}", new Date());
        return new Date().toString();
    }

    @GetMapping("getSaTokenConfig")
    public SaTokenConfig getSaTokenConfig() {
        log.info("Sa-Token配置：{}", SaManager.getConfig());
        return SaManager.getConfig();
    }

    @RequestMapping("/dologin")
    public BlogData userlogin(@RequestParam("username") String username,
                              @RequestParam("password") String password){
        if ("11".equals(username) && "22".equals(password)){
            StpUtil.logout();
            StpUtil.login(1);
            return new BlogData().setcode(200).seterror("登录成功").setcode(StpUtil.getLoginId(1));
        }
        return new BlogData().setcode(400).seterror("登录失败").setcode(StpUtil.getLoginId(1));
    }

    @RequestMapping("/logout")
    public BlogData logout(String loginId) {
        StpUtil.logout(loginId);
        return new BlogData().setcode(200).seterror("正常退出");
    }

}
