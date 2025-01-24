package com.cnlbc.service;

import com.cnlbc.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherService {
    public List<Teacher> findAllTeacher();

    public Teacher findTeacherById(Integer teacherId);

    public void addTeacher(Teacher teacher);

    public void updateTeacher(Teacher teacher);

    public Integer deleteTeacher(Integer teacherId);

    public int countTeachers();

    public Integer getMaxTeacherId();

    public List<Teacher> findTeacherByIdOrName(@Param("searchTerm") String searchTerm, @Param("offset") int offset, @Param("pageSize") int pageSize);

    public int countTeachersByIdOrName(@Param("searchTerm") String searchTerm);

    public List<Teacher> findTeachersWithDepartmentAndClassesByTeacherId(@Param("teacherId") Integer teacherId);
}