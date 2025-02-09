package com.cnlbc.repository;

import com.cnlbc.pojo.Class;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository{
        
    List<Class> getAllClasses();

    List<Class> findClassByIdOrName(@Param("searchTerm") String searchTerm,@Param("page") int page,@Param("pageSize") int pageSize);

    int countClassesByIdOrName(String searchTerm);


    Class getClassById(String id);
    
    void saveClass(Class clazz);
    
    void updateClass(Class clazz);
    
    void deleteClassById(String id);
}

