package com.cnlbc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Initialize {
    @GetMapping("/") // 处理根路径的GET请求
    public String redirectToLogin() {
        return "redirect:/user/loginpage"; // 重定向到登录页面
    }
}