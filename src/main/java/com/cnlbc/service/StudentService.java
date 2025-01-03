package com.cnlbc.service;

import com.cnlbc.pojo.Student;
import com.cnlbc.repository.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// StudentService.java
@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public List<Student> getStudentsByClassId(String classId) {
        return studentMapper.getStudentsByClassId(classId);
    }
}