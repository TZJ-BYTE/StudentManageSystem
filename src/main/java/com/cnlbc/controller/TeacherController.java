package com.cnlbc.controller;

import com.cnlbc.pojo.Teacher;
import com.cnlbc.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
public class TeacherController {



    @Autowired
    private TeacherService teacherService;

    @GetMapping("/findall")
    public Map<String, Object> findAllTeachers(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {


        List<Teacher> teachers = teacherService.findAllTeacher();


        // 计算总页数
        int totalTeachers = teacherService.countTeachers();
        int totalPages = (int) Math.ceil((double) totalTeachers / pageSize);

        Map<String, Object> response = new HashMap<>();
        response.put("teachers", teachers);
        response.put("currentPage", page);
        response.put("pageSize", pageSize);
        response.put("totalPages", totalPages);

        return response;
    }



    @GetMapping("/{teacherId}")
    public Teacher findTeacherById(@PathVariable String teacherId) {
        return teacherService.findTeacherById(Integer.valueOf(teacherId));
    }

    @PostMapping("/add")
    public String addTeacher(@RequestBody Teacher teacher) {
        teacherService.addTeacher(teacher);
        return "success";
    }
    @GetMapping("/getMaxTeacherId")
    public Integer getMaxTeacherId() {
        return teacherService.getMaxTeacherId();
    }
    @PutMapping("/edit/{teacherId}")
    public String editTeacher(@PathVariable String teacherId, @RequestBody Teacher teacher) {
        teacher.setTeacherId(Integer.valueOf(teacherId));
        teacherService.updateTeacher(teacher);
        return "success";
    }

    @DeleteMapping("/delete/{teacherId}")
    public String deleteTeacher(@PathVariable String teacherId) {
        teacherService.deleteTeacher(Integer.valueOf(teacherId));
        return "success";
    }
}