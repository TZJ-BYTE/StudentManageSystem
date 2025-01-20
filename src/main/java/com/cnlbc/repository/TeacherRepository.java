package com.cnlbc.repository;

import com.cnlbc.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeacherRepository {
    public List<Teacher> findAllTeacher();

    public Teacher findTeacherById(Integer teacherId);

    public void addTeacher(Teacher teacher);

    public void updateTeacher(Teacher teacher);

    public void deleteTeacher(Integer teacherId);

    public int countTeachers();

    public Integer getMaxTeacherId();

    public List<Teacher> findTeacherByIdOrName(@Param("searchTerm") String searchTerm, @Param("offset") int offset, @Param("pageSize") int pageSize);

    public int countTeachersByIdOrName(@Param("searchTerm") String searchTerm);
}