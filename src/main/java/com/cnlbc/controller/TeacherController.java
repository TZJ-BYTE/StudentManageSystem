package com.cnlbc.controller;

import com.cnlbc.pojo.Teacher;
import com.cnlbc.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/teachers")
    public Map<String, Object> getTeachers(@RequestParam(required = false) Integer page,
                                           @RequestParam(required = false) Integer pageSize) {
        Map<String, Object> response = new HashMap<>();
        List<Teacher> teachers = teacherService.getTeachers(page, pageSize);
        response.put("code", 200);
        response.put("message", "success");
        response.put("data", teachers);
        return response;
    }
}