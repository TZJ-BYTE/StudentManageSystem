//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.cnlbc.controller;

import com.cnlbc.pojo.User;
import com.cnlbc.service.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static jdk.nashorn.internal.runtime.GlobalFunctions.decodeURIComponent;

@Controller
@RequestMapping({"/home"})
public class HomeController {
    @Autowired
    private UserService userService;
    private User user;

    public HomeController() {
    }

    @RequestMapping("/findAllUser")
    public String home(Model model, HttpServletRequest request, HttpServletResponse response){
            List<User> users = this.userService.findAllUser();
            model.addAttribute("list", users);
        return "home";
    }
    //进入主页面

    //进入教师管理页面
    @RequestMapping("/teachermanage")
    public String teachermanage(){
        return "teacher";
    }

    //进入班级管理页面
    @RequestMapping("/classmanage")
    public String classmanage(){
        return "class";
    }
    //更改密码
    // 处理 GET 请求，从请求参数中获取原始密码和新密码

    @GetMapping("/updatepasswd")
    public ResponseEntity<Boolean> updatePassword(@RequestParam String originalPassword, @RequestParam String newPassword, HttpSession session) {
        boolean result;
        user = (User) session.getAttribute("user");
        if(user.getPassword().equals(originalPassword)){
            try {
                // 调用服务层方法来更新密码
                userService.updatePassword(user.getUsertid(), newPassword);
                result=true;
                user.setPassword(newPassword);
            } catch (Exception e) {
                e.printStackTrace();
                // 如果发生异常，则设置为false
                result = false;
            }
        }
        else result=false;
        // 返回响应实体，包含结果和适当的HTTP状态码
        return ResponseEntity.ok(result);
    }

    // 更改用户名
    @GetMapping("/updateusername")
    public ResponseEntity<Boolean> updateUsername(@RequestParam String newValue, HttpSession session) {
        Boolean result;
        System.out.println("新的昵称是" + newValue);
        user = (User) session.getAttribute("user");
        int usertid = user.getUsertid();
        System.out.println("用户id是" + usertid);
        try {
            // 这里处理newValue
            userService.updateUserName(usertid, newValue);
            result = true;
            user.setUsername(newValue);
        } catch (Exception e) {
            // 如果发生异常，则设置为false
            result = false;
        }
        System.out.println("New nickname: " + newValue);
        System.out.println(result);
        // 返回响应实体，包含结果和适当的HTTP状态码
        return ResponseEntity.ok(result);
    }


}
