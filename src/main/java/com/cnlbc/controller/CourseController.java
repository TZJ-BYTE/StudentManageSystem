package com.cnlbc.controller;
import com.cnlbc.pojo.Course;
import com.cnlbc.pojo.Msg;
import com.cnlbc.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

@Controller
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @GetMapping("/findall")
    public Map<String, Object> findAllCourses(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Course> courses = courseService.findAllCourses();
        // 计算总页数
        int totalCourses = courseService.countCourses();
        int totalPages = (int) Math.ceil((double) totalCourses / pageSize);
        Map<String, Object> response = new HashMap<>();
        response.put("courses", courses);
        response.put("currentPage", page);
        response.put("pageSize", pageSize);
        response.put("totalPages", totalPages);
        System.out.println(courses);
        return response;
    }
    @GetMapping("/get/{courseId}")
    public Course findCourseById(@PathVariable String courseId) {
        return courseService.findCourseById(courseId);
    }

    @PostMapping("/add")
    public Msg addCourse(@RequestBody Course course) {
        Msg message = new Msg(false, "添加课程");
        try {
            courseService.addCourse(course);
            message.setSuccess(true);
            message.setMessage("课程添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            message.setSuccess(false);
            message.setMessage("课程添加失败");
        }
        return message;
    }

    @GetMapping("/getMaxCourseId")
    public Integer getMaxCourseId() {
        return courseService.getMaxCourseId();
    }

    @PutMapping("/edit/{courseId}")
    public Msg editCourse(@PathVariable String courseId, @RequestBody Course course) {
        Msg message = new Msg(false, "修改课程");
        course.setCourseId(String.valueOf(Integer.valueOf(courseId)));
        try {
            courseService.updateCourse(course);
            message.setSuccess(true);
            message.setMessage("课程修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            message.setSuccess(false);
            message.setMessage("课程修改失败");
        }
        return message;
    }
    @DeleteMapping("/delete/{courseId}")
    public String deleteCourse(@PathVariable String courseId) {
        courseService.deleteCourse(courseId);
        return "success";
    }

    // 新增方法：根据ID或名称进行模糊查询
    @GetMapping("/search")
    @ResponseBody
    public Msg findCourseByIdOrName(
            @RequestParam(value = "searchTerm") String searchTerm,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        try {
            // 获取分页结果
            List<Course> courses = courseService.findCourseByIdOrName(searchTerm, page, pageSize);
            // 计算总页数
            int totalCourses = courseService.countCourseByIdOrName(searchTerm);
            int totalPages = (int) Math.ceil((double) totalCourses / pageSize);
            Map<String, Object> data = new HashMap<>();
            data.put("courses", courses);
            data.put("currentPage", page);
            data.put("pageSize", pageSize);
            data.put("totalPages", totalPages);

            // 封装到 Msg 对象中
            Msg msg = new Msg();
            msg.setSuccess(true);
            msg.setMessage("查询成功");
            msg.setData(data);
            System.out.println("课程msg"+msg);
            return msg;
        } catch (Exception e) {
            // 处理异常，记录日志，返回错误消息
            e.printStackTrace(); // 这里可以替换为更详细的日志记录
            Msg msg = new Msg();
            msg.setSuccess(false);
            msg.setMessage("查询失败：" + e.getMessage());
            System.out.println("课程msg"+msg);
            return msg;
        }
    }

}