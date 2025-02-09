package com.cnlbc.controller;

import com.cnlbc.pojo.Class;
import com.cnlbc.pojo.Teacher;
import com.cnlbc.service.ClassService;
import com.cnlbc.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

