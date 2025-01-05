package com.cnlbc.service;

import com.cnlbc.pojo.Teacher;
import com.cnlbc.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> findAllTeacher() {
        System.out.println("findAllTeacher"+teacherRepository.findAllTeacher());
        return teacherRepository.findAllTeacher();
    }

    @Override
    public Teacher findTeacherById(String teacherId) {
        return teacherRepository.findTeacherById(teacherId);
    }

    @Override
    public void addTeacher(Teacher teacher) {
        teacherRepository.addTeacher(teacher);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teacherRepository.updateTeacher(teacher);
    }

    @Override
    public void deleteTeacher(String teacherId) {
        teacherRepository.deleteTeacher(teacherId);
    }

    @Override
    public int countTeachers() {
        return teacherRepository.countTeachers();
    }
}
