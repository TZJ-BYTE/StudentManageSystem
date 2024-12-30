//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.cnlbc.controller;

import com.cnlbc.pojo.User;
import com.cnlbc.service.UserService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/home"})
public class HomeController {
    @Autowired
    private UserService userService;

    public HomeController() {
    }

    @RequestMapping("/findAllUser")
    public String home(Model model, HttpServletRequest request, HttpServletResponse response){
            List<User> users = this.userService.findAllUser();
            model.addAttribute("list", users);
        return "home";
    }
}
