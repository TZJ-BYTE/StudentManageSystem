package com.cnlbc.service;
import com.cnlbc.pojo.Course;

import java.util.List;

public interface CourseService {
    Course getCourseById(String courseId);
    int addCourse(Course course);
    int updateCourse(Course course);
    int deleteCourse(String courseId);
    List<Course> getCoursesByDepartmentId(String departmentId);
}