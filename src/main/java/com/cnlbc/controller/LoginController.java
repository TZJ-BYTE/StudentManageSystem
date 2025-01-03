package com.cnlbc.controller;

import com.cnlbc.pojo.User;
import com.cnlbc.pojo.Msg;
import com.cnlbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

    public LoginController() {
    }

    @PostMapping(consumes = "application/json")
    @RequestMapping("/login")
    @ResponseBody
    public Msg login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Msg message = new Msg();
        if (validateUser(username, password)) {
            message.setMessage("登录成功");
            message.setSuccess(true);
        } else {
            message.setMessage("登录失败");
            message.setSuccess(false);
        }
        return message;
    }

    @RequestMapping("/loginpage")
    public String Loginpage(Model model){
        //进入login页面
        return "login";
    }

    //进入注册页面
    @RequestMapping("/registerpage")
    public String Register(){
        //进入注册页面
        return "register";
    }

    //用户注册
    @RequestMapping("/register")
    @ResponseBody
    public Msg Registe() {
        Msg message = new Msg();
        message.setMessage("注册成功");
        message.setSuccess(true);
        return message;
    }
    //进入查看信息页面
    @RequestMapping("/mepage")
    public String Mepage(){
        //进入查看信息页面
        return "me";
    }


    //测试
    @RequestMapping("/success")
    public String success(){
        return "success";
    }

    private boolean validateUser(String username, String password) {
        if (userService == null) {
            throw new IllegalStateException("UserService未初始化");
        }
        List<User> users = userService.findUserByName(username);
        if (users != null && !users.isEmpty()) {
            User user = users.get(0);
            return user.getPassword().equals(password);
        }
        return false;
    }
    //进入更改密码页面
    @RequestMapping("/me/passwordpage")
    public String passwordpage() {
        return "mepassword";
    }
    //更改密码
    @RequestMapping("/me/password")
    @ResponseBody
    public Msg password() {
        Msg message = new Msg();
        message.setMessage("密码修改成功");
        message.setSuccess(true);
        return message;
    }

}
