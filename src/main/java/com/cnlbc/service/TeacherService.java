package com.cnlbc.service;

import com.cnlbc.pojo.Teacher;

import java.util.List;

public interface TeacherService {
    public List<Teacher> findAllTeacher();

    public Teacher findTeacherById(String teacherId);

    public void addTeacher(Teacher teacher);

    public void updateTeacher(Teacher teacher);

    public void deleteTeacher(String teacherId);

    public int countTeachers();
}
