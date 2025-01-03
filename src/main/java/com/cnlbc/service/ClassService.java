package com.cnlbc.service;

import com.cnlbc.repository.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// ClassService.java
@Service
public class ClassService {
    @Autowired
    private ClassMapper classMapper;

    public String addClass(Class clazz) {
        classMapper.addClass(clazz);
        return String.valueOf(clazz);
    }

    public void updateClass(Class clazz) {
        classMapper.updateClass(clazz);
    }

    public void deleteClass(String classId) {
        classMapper.deleteClass(classId);
    }

    public List<Class> getClasses(String grade, String className) {
        return classMapper.getClasses(grade, className);
    }

    public Class getClassById(String classId) {
        return classMapper.getClassById(classId);
    }
}
