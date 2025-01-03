package com.cnlbc.service;

import com.cnlbc.pojo.Course;
import com.cnlbc.repository.CourseMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseMapper courseMapper;
    private Course course;

    @Override
    public Course getCourseById(String courseId) {
        return courseMapper.getCourseById(courseId);
    }

//    @Override
//    public int addCourse(Course course) {
//        this.course = course;
//        return 0;
//    }

    @Override
    public int addCourse(Course course) {
        this.course = course;
        return courseMapper.addCourse(course);
    }

    @Override
    public int updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    @Override
    public int deleteCourse(String courseId) {
        return courseMapper.deleteCourse(courseId);
    }

    @Override
    public List<Course> getCoursesByDepartmentId(String departmentId) {
        return courseMapper.getCoursesByDepartmentId(departmentId);
    }
}