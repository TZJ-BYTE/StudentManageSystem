package com.cnlbc.service;
import com.cnlbc.pojo.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseService {
    List<Course> findAllCourses();



    Course findCourseById(String courseId);

    void addCourse(Course course);

    void updateCourse(Course course);


    String deleteCourse(String courseId);

    int countCourses();

    Integer getMaxCourseId();

    List<Course> findCourseByIdOrName(@Param("searchTerm") String searchTerm, @Param("offset") int offset, @Param("pageSize") int pageSize);

    int countCourseByIdOrName(@Param("searchTerm") String searchTerm);
}