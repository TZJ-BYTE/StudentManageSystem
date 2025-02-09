package com.cnlbc.service;

import com.cnlbc.pojo.Class;
import com.cnlbc.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    
    @Autowired
    private ClassRepository classRepository;
    
    @Override
    public List<Class> getAllClasses() {
        return classRepository.getAllClasses();
    }
    
    @Override
    public Class getClassById(String id) {
        return classRepository.getClassById(id);
    }

    @Override
    public void saveClass(Class clazz) {
        classRepository.saveClass(clazz);
    }

    @Override
    public void updateClass(Class clazz) {
        // TODO: 实现更新班级的逻辑
    }
    
    @Override
    public void deleteClassById(String id) {
        classRepository.deleteClassById(id);
    }
}
