package com.cnlbc.controller;

import com.cnlbc.pojo.Class;
import com.cnlbc.pojo.Msg;
import com.cnlbc.pojo.Teacher;
import com.cnlbc.service.ClassService;
import com.cnlbc.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ClassController.java
@Controller
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public String listClasses(Model model) {
        List<Class> classList = classService.getAllClasses();
        List<Teacher> teacherList = teacherService.findAllTeacher();
        model.addAttribute("classList", classList);
        model.addAttribute("teacherList", teacherList);
        return "class";
    }


    @GetMapping("/search")
    @ResponseBody // 明确表示返回JSON数据
    public Msg findClassByIdOrName(
            @RequestParam(value = "searchTerm") String searchTerm,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        // 获取分页结果
        List<Class> classes = classService.findClassByIdOrName(searchTerm, page, pageSize);
        // 计算总页数
        int totalClasses = classService.countClassesByIdOrName(searchTerm);
        int totalPages = (int) Math.ceil((double) totalClasses / pageSize);

        Map<String, Object> data = new HashMap<>();
        data.put("classes", classes);
        data.put("currentPage", page);
        data.put("pageSize", pageSize);
        data.put("totalPages", totalPages);

        // 封装到 Msg 对象中
        Msg msg = new Msg();
        msg.setSuccess(true);
        msg.setMessage("查询成功");
        msg.setData(data);

        return msg;
    }





    @PostMapping("/add")
    public String addClass(Class clazz) {
        classService.saveClass(clazz);
        return "redirect:/class";
    }

    @GetMapping("/edit/{id}")
    public String showEditClassForm(@PathVariable("id") String id, Model model) {
        Class clazz = classService.getClassById(id);
        List<Teacher> teacherList = teacherService.findAllTeacher();
        model.addAttribute("class", clazz);
        model.addAttribute("teacherList", teacherList);
        return "class-edit";
    }

    @PostMapping("/edit")
    public String updateClass(Class clazz) {
        classService.updateClass(clazz);
        return "redirect:/class";
    }

    @PostMapping("/delete/{id}")
    public String deleteClass(@PathVariable("id") String id) {
        classService.deleteClassById(id);
        return "redirect:/class";
    }
}

