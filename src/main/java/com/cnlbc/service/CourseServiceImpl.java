package com.cnlbc.service;

import com.cnlbc.pojo.Course;
import com.cnlbc.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> findAllCourses() {
        System.out.println("findAllCourses: " + courseRepository.findAllCourses());
        return courseRepository.findAllCourses();
    }

    @Override
    public Course findCourseById(String courseId) {
        return courseRepository.findCourseById(courseId);
    }



    @Override
    public void addCourse(Course course) {
        courseRepository.addCourse(course);
    }

    @Override
    public void updateCourse(Course course) {
        courseRepository.updateCourse(course);
    }

    @Override
    public String deleteCourse(String courseId) {
        courseRepository.deleteCourse(courseId);
        return courseId; // 假设你希望返回被删除的课程ID，这里保持返回类型为 Integer
    }



    @Override
    public int countCourses() {
        return courseRepository.countCourses();
    }

    @Override
    public Integer getMaxCourseId() {
        return courseRepository.getMaxCourseId();
    }

    @Override
    public List<Course> findCourseByIdOrName(String searchTerm, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return courseRepository.findCourseByIdOrName(searchTerm, offset, pageSize);
    }

    @Override
    public int countCourseByIdOrName(String searchTerm) {
        return courseRepository.countCourseByIdOrName(searchTerm);
    }
}
