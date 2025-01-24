package com.cnlbc.controller;

import com.cnlbc.pojo.Msg;
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
        // 计算偏移量
        int offset = (page - 1) * pageSize;
        // 获取分页结果
        List<Teacher> paginatedTeachers = teacherService.findTeacherByIdOrName("", offset, pageSize);
        // 计算总页数
        int totalTeachers = teacherService.countTeachers();
        int totalPages = (int) Math.ceil((double) totalTeachers / pageSize);
        Map<String, Object> response = new HashMap<>();
        response.put("teachers", paginatedTeachers);
        response.put("currentPage", page);
        response.put("pageSize", pageSize);
        response.put("totalPages", totalPages);
        return response;
    }

    @GetMapping("/get/{teacherId}")
    public Teacher findTeacherById(@PathVariable int teacherId) {
        return teacherService.findTeacherById(teacherId);
    }

    @GetMapping("/getmore/{teacherId}")
    public Teacher findMoreById(@PathVariable int teacherId) {
        List<Teacher> teachers = teacherService.findTeachersWithDepartmentAndClassesByTeacherId(teacherId);
        if (teachers != null && !teachers.isEmpty()) {
            return teachers.get(0); // 每个教师 ID 对应唯一记录
        } else {
            return null;
        }
    }


    @PostMapping("/add")
    public Msg addTeacher(@RequestBody Teacher teacher) {
        Msg message = new Msg(false, "添加教师");
        try {
            teacherService.addTeacher(teacher);
            message.setSuccess(true);
            message.setMessage("教师添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            message.setSuccess(false);
            message.setMessage("教师添加失败");
        }
        return message;
    }

    @GetMapping("/getMaxTeacherId")
    public Integer getMaxTeacherId() {
        return teacherService.getMaxTeacherId();
    }

    @PutMapping("/edit/{teacherId}")
    public Msg editTeacher(@PathVariable String teacherId, @RequestBody Teacher teacher) {
        Msg message = new Msg(false, "修改教师");
        teacher.setTeacherId(Integer.valueOf(teacherId));
        try {
            teacherService.updateTeacher(teacher);
            message.setSuccess(true);
            message.setMessage("教师修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            message.setSuccess(false);
            message.setMessage("教师修改失败");
        }
        return message;
    }

    @DeleteMapping("/delete/{teacherId}")
    public String deleteTeacher(@PathVariable String teacherId) {
        teacherService.deleteTeacher(Integer.valueOf(teacherId));
        return "success";
    }

    @GetMapping("/search")
    public Msg findTeacherByIdOrName(
            @RequestParam(value = "searchTerm") String searchTerm,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        // 获取分页结果
        List<Teacher> teachers = teacherService.findTeacherByIdOrName(searchTerm, page, pageSize);
        // 计算总页数
        int totalTeachers = teacherService.countTeachersByIdOrName(searchTerm);
        int totalPages = (int) Math.ceil((double) totalTeachers / pageSize);

        Map<String, Object> data = new HashMap<>();
        data.put("teachers", teachers);
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
}

