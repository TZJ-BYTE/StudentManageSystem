package com.cnlbc.repository;
import com.cnlbc.pojo.Course;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface CourseMapper {
    // 根据课程ID查询课程信息
    @Select("SELECT * FROM course WHERE courseId = #{courseId}")
    Course getCourseById(@Param("courseId") String courseId);

    // 插入新学科信息
    @Insert("INSERT INTO course (courseName, description, departmentId) VALUES (#{courseName}, #{description}, #{departmentId})")
    @Options(useGeneratedKeys = true, keyProperty = "courseId")
    int addCourse(Course course);

    // 根据课程ID更新课程信息
    @Update("UPDATE course SET courseName = #{courseName}, description = #{description}, departmentId = #{departmentId} WHERE courseId = #{courseId}")
    int updateCourse(Course course);

    // 根据课程ID删除课程
    @Delete("DELETE FROM course WHERE courseId = #{courseId}")
    int deleteCourse(String courseId);

    // 根据学院ID获取该学院下所有课程
    @Select("SELECT * FROM course WHERE departmentId = #{departmentId}")
    List<Course> getCoursesByDepartmentId(@Param("departmentId") String departmentId);
}