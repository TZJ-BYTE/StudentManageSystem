package com.cnlbc.repository;

import com.cnlbc.pojo.Student;

import java.util.List;

// StudentMapper.java
public interface StudentRepository {
    List<Student> getStudentsByClassId(String classId);
}
