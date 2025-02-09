package com.cnlbc.repository;

import com.cnlbc.pojo.Class;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository{
        
    List<Class> getAllClasses();
    
    Class getClassById(String id);
    
    void saveClass(Class clazz);
    
    void updateClass(Class clazz);
    
    void deleteClassById(String id);
}

