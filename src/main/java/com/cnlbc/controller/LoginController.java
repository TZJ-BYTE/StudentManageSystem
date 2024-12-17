package com.cnlbc.controller;

import com.cnlbc.pojo.User;
import com.cnlbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    public LoginController() {
    }

    @PostMapping
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            // 处理无效输入
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "用户名或密码不能为空");
            return "redirect:/login.jsp";
        }

        if (validateUser(username, password)) {
            return "redirect:/home/findAllUser";
        } else {
            // 登录失败处理逻辑
            return "redirect:/login.jsp";
        }
    }

    private boolean validateUser(String username, String password) {
        if (userService == null) {
            throw new IllegalStateException("UserService is not initialized");
        }
        List<User> users = userService.findUserByName(username);
        if (users != null && !users.isEmpty()) {
            User user = users.get(0);
            return user.getPassword().equals(password);
        }
        return false;
    }
}
