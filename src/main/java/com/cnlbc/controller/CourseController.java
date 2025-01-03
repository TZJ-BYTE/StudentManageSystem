package com.cnlbc.controller;
import com.cnlbc.pojo.Course;
import com.cnlbc.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import javax.annotation.Resource;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Resource
    private CourseService courseService;

    // 根据课程ID查询课程信息
    @GetMapping("/{courseId}")
    @ResponseBody
    public Course getCourseById(@PathVariable("courseId") String courseId) {
        return courseService.getCourseById(courseId);
    }

    // 添加新学科
    @PostMapping
    @ResponseBody
    public Object addCourse(@RequestBody Course course) {
        int result = courseService.addCourse(course);
        if (result > 0) {
            return new ResponseMessage("学科添加成功", course.getCourseId());
        } else {
            return new ResponseMessage("学科添加失败");
        }
    }

    // 更新学科信息
    @PutMapping("/{courseId}")
    @ResponseBody
    public Object updateCourse(@PathVariable("courseId") String courseId, @RequestBody Course course) {
        course.setCourseId(courseId);
        int result = courseService.updateCourse(course);
        if (result > 0) {
            return new ResponseMessage("学科信息更新成功");
        } else {
            return new ResponseMessage("学科信息更新失败");
        }
    }

    // 删除学科
    @DeleteMapping("/{courseId}")
    @ResponseBody
    public Object deleteCourse(@PathVariable("courseId") String courseId) {
        int result = courseService.deleteCourse(courseId);
        if (result > 0) {
            return new ResponseMessage("学科删除成功");
        } else {
            return new ResponseMessage("学科删除失败");
        }
    }

    // 根据学院ID获取该学院下的所有学科
    @GetMapping("/departments/{departmentId}/courses")
    @ResponseBody
    public List<Course> getCoursesByDepartmentId(@PathVariable("departmentId") String departmentId) {
        return courseService.getCoursesByDepartmentId(departmentId);
    }

    // 内部类，用于封装响应消息
    private class ResponseMessage {
        private String message;
        private String courseId;

        public ResponseMessage(String message) {
            this.message = message;
        }

        public ResponseMessage(String message, String courseId) {
            this.message = message;
            this.courseId = courseId;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }
    }
}