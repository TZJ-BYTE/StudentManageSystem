package com.cnlbc.controller;


import com.cnlbc.pojo.Msg;
import com.cnlbc.pojo.User;
import com.cnlbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUser")
    public String findAllUser(Model model){
        List<User> list=userService.findAllUser();
        model.addAttribute("list",list);
        return "AllUser";
    }
    //测试
    @RequestMapping("/success")
    public String success(){
        return "Success";
    }
    //进入login页面
    @RequestMapping("/Loginpage")
    public String Loginpage(Model model){
        //进入login页面
        return "Login";
    }
    //用户登录
    @RequestMapping("/Login")
    @ResponseBody
    public Msg login() {
        Msg message = new Msg();
        message.setMessage("登录成功");
        message.setSuccess(true);
        return message;
    }
    //进入注册页面
    @RequestMapping("/registerpage")
    public String register(){
        //进入注册页面
        return "Register";
    }
    //用户注册
    @RequestMapping("/register")
    @ResponseBody
    public Msg registe() {
        Msg message = new Msg();
        message.setMessage("注册成功");
        message.setSuccess(true);
        return message;
    }
    //进入查看信息页面
    @RequestMapping("/mepage")
    public String mepage(){
        //进入注册页面
        return "Me";
    }
    //用户注册
    @RequestMapping("/me")
    @ResponseBody
    public Msg me() {
        Msg message = new Msg();
        message.setMessage("注册成功");
        message.setSuccess(true);
        return message;
    }
}