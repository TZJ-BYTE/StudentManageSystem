package com.cnlbc.service;

import com.cnlbc.pojo.Teacher;

import java.util.List;

public interface TeacherService {
    public List<Teacher> findAllTeacher();

    public Teacher findTeacherById(Integer teacherId);

    public void addTeacher(Teacher teacher);

    public void updateTeacher(Teacher teacher);

    public Integer deleteTeacher(Integer teacherId);

    public int countTeachers();

    public Integer getMaxTeacherId();
}