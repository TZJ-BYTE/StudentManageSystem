package com.cnlbc.service;

import com.cnlbc.pojo.Class;

import java.util.List;

public interface ClassService {
    
    List<Class> getAllClasses();

    List<Class> findClassByIdOrName(String searchTerm, int page, int pageSize);

    int countClassesByIdOrName(String searchTerm);
    
    Class getClassById(String id);
    
    void saveClass(Class clazz);
    
    void updateClass(Class clazz);
    
    void deleteClassById(String id);


}
