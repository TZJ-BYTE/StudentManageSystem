package com.cnlbc.repository;

import com.cnlbc.pojo.Course;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface CourseRepository {
    List<Course> findAllCourses();

    Course findCourseById(@Param("courseId") String courseId);

    void addCourse(Course course);

    void updateCourse(Course course);

    String deleteCourse(@Param("courseId") String courseId);

    int countCourses();

    int getMaxCourseId();

    List<Course> findCourseByIdOrName(@Param("searchTerm") String searchTerm,
                                      @Param("offset") int offset,
                                      @Param("pageSize") int pageSize);

    int countCourseByIdOrName(@Param("searchTerm") String searchTerm);
}
