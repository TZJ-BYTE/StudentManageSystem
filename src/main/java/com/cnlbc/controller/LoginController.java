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
            // 获取完整的用户对象
            User loggedInUser = userService.findUserByName(username).get(0);
            session.setAttribute("user", loggedInUser); // 将用户对象存入会话
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
            userService.registerUser(user);
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






   @GetMapping("/getMaxUsertId")
   @ResponseBody
   public ResponseEntity<Integer> getMaxUsertId() {
       Integer maxUsertId = userService.getMaxUsertId();
       return ResponseEntity.ok(maxUsertId);
   }
@GetMapping("/logout")
public String logout(HttpSession session) {
    session.invalidate(); // 清除会话中的所有信息
    return "redirect:/user/loginpage"; // 重定向到登录页面
}

}
