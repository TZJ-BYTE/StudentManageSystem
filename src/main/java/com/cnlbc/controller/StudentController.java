package com.cnlbc.controller;

import com.cnlbc.pojo.Student;
import com.cnlbc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// StudentController.java
@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/class/{classId}")
    public ResponseEntity<Map<String, Object>> getStudentsByClassId(@PathVariable String classId) {
        List<Student> students = studentService.getStudentsByClassId(classId);
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "success");
        response.put("data", students);
        return ResponseEntity.ok(response);
    }
}