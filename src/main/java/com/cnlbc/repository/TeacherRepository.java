package com.cnlbc.repository;

import com.cnlbc.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherRepository {
    public List<Teacher> findAllTeacher();

    public Teacher findTeacherById(String teacherId);

    public void addTeacher(Teacher teacher);

    public void updateTeacher(Teacher teacher);

    public void deleteTeacher(String teacherId);

    public int countTeachers();
}
