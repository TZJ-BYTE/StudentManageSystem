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
    public Teacher findTeacherById(Integer teacherId) {
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
    public Integer deleteTeacher(Integer teacherId) {
        teacherRepository.deleteTeacher(teacherId);
        return teacherId;
    }

    @Override
    public int countTeachers() {
        return teacherRepository.countTeachers();
    }

    @Override
    public Integer getMaxTeacherId() {
        return teacherRepository.getMaxTeacherId();
    }

    @Override
    public List<Teacher> findTeacherByIdOrName(String searchTerm, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return teacherRepository.findTeacherByIdOrName(searchTerm, offset, pageSize);
    }

    @Override
    public int countTeachersByIdOrName(String searchTerm) {
        return teacherRepository.countTeachersByIdOrName(searchTerm);
    }

    @Override
    public List<Teacher> findTeachersWithDepartmentAndClassesByTeacherId(Integer teacherId) {
        return teacherRepository.findTeachersWithDepartmentAndClassesByTeacherId(teacherId);
    }
}