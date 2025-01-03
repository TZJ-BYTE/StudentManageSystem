package com.cnlbc.repository;

import org.apache.ibatis.annotations.Param;

import java.util.List;

// ClassMapper.java
public interface ClassMapper {
    void addClass(Class clazz);
    void updateClass(Class clazz);
    void deleteClass(String classId);
    List<Class> getClasses(@Param("grade") String grade, @Param("className") String className);
    Class getClassById(String classId);
}

