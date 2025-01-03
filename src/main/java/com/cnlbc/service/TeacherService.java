package com.cnlbc.service;

import com.cnlbc.pojo.Teacher;
import com.cnlbc.repository.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    public List<Teacher> getTeachers(Integer page, Integer pageSize) {
        if (page == null || page < 1) page = 1;
        if (pageSize == null || pageSize < 1) pageSize = 10;
        int offset = (page - 1) * pageSize;
        return teacherMapper.selectTeachers(offset, pageSize);
    }
}