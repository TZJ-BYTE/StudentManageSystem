package com.cnlbc.controller;

import com.cnlbc.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ClassController.java
@RestController
@RequestMapping("/api/classes")
public class ClassController {
    @Autowired
    private ClassService classService;

    @PostMapping
    public ResponseEntity<Map<String, String>> addClass(@RequestBody Class clazz) {
        String classId = classService.addClass(clazz);
        Map<String, String> response = new HashMap<>();
        response.put("message", "班级添加成功");
        response.put("classId", classId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{classId}")
    public ResponseEntity<Map<String, String>> updateClass(@PathVariable String classId, @RequestBody Class clazz) {
        clazz.getClass();
        classService.updateClass(clazz);
        Map<String, String> response = new HashMap<>();
        response.put("message", "班级信息更新成功");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{classId}")
    public ResponseEntity<Map<String, String>> deleteClass(@PathVariable String classId) {
        classService.deleteClass(classId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "班级删除成功");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getClasses(
            @RequestParam(required = false) String grade,
            @RequestParam(required = false) String className,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        List<Class> classes = classService.getClasses(grade, className);
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "success");
        response.put("data", classes);
        response.put("total", classes.size());
        response.put("page", page);
        response.put("pageSize", pageSize);
        return ResponseEntity.ok(response);
    }
}

