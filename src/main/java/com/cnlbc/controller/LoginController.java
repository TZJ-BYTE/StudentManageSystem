package com.cnlbc.controller;

import com.cnlbc.pojo.User;
import com.cnlbc.pojo.Msg;
import com.cnlbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.util.List;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;
    private User user;

    public LoginController() {
    }

    @PostMapping(consumes = "application/json")
    @RequestMapping("/login")
    @ResponseBody
    public Msg login(@RequestBody User user, HttpSession session) {
        String username = user.getUsername();
        String password = user.getPassword();
        Msg message = new Msg();
        if (validateUser(username, password)) {
            session.setAttribute("user", user); // 将用户对象存入会话
            message.setMessage("登录成功");
            message.setSuccess(true);
        } else {
            message.setMessage("登录失败");
            message.setSuccess(false);
        }
        return message;
    }
    //进入login页面
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
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Msg register(@RequestBody User user) { // 假设User类有username和password字段
        Msg message = new Msg(false, "注册账号");
        try {
            userService.insertUser(user.getUsername(), user.getPassword());
            message.setSuccess(true);
            message.setMessage("注册成功");
        } catch (Exception e) {
            message.setSuccess(false);
            message.setMessage("注册失败");
        }
        return message;
    }
    //进入主页面
    @RequestMapping("/allfunspage")
    public ModelAndView homepage(HttpSession session){
        // 从会话中获取用户信息
        User user = (User) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView("allfuns");
        if (user != null) {
            modelAndView.addObject("usermsg", user.getUsername());
        } else {
            modelAndView.addObject("usermsg", "未登录或用户信息丢失");
        }
        return modelAndView;
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
    // 处理 GET 请求，从请求参数中获取原始密码和新密码
    @GetMapping("/updatepasswd")
    public ResponseEntity<Boolean> updatePassword(@RequestParam String originalPassword,@RequestParam String newPassword,HttpSession session) {
        boolean result;
        User user = (User) session.getAttribute("user");
        if(userService.findByPasswd(user.getUsername(),originalPassword)!=null){
            try {
                // 调用服务层方法来更新密码
                userService.updatePassword(user.getUsername(), newPassword);
                result=true;
            } catch (Exception e) {
                // 如果发生异常，则设置为false
                result = false;
            }
        }
       else result=false;
        // 返回响应实体，包含结果和适当的HTTP状态码
        return ResponseEntity.ok(result);
    }

    //更改用户
    @GetMapping("/updateusername")
    public ResponseEntity<Boolean> updateUsername(@RequestParam String newValue, HttpSession session) {
        Boolean result;
        System.out.println("新的昵称是" + escapeSingleQuotes(newValue) + "");
        User user = (User) session.getAttribute("user");
        String username= user.getUsername();
        System.out.println("旧的昵称是" + escapeSingleQuotes(user.getUsername()) + "");
        try {
            // 这里处理newValue
            userService.updateUserName(escapeSingleQuotes(username), escapeSingleQuotes(newValue));
            result = true;
        } catch (Exception e) {
            // 如果发生异常，则设置为false
            result = false;
        }
        System.out.println("New nickname: '" + escapeSingleQuotes(newValue) + "'");
        System.out.println(result);
        // 返回响应实体，包含结果和适当的HTTP状态码
        return ResponseEntity.ok(result);
    }

    // 方法来处理字符串，为其添加单引号
    private String escapeSingleQuotes(String input) {
        return "'" + input.replace("'", "''") + "'";
    }


   @GetMapping("/getMaxUsertId")
   @ResponseBody
   public ResponseEntity<Integer> getMaxUsertId() {
       Integer maxUsertId = userService.getMaxUsertId();
       return ResponseEntity.ok(maxUsertId);
   }

}
