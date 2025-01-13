package com.cnlbc.repository;

import com.cnlbc.pojo.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentRepository {
    public List<Department> findAllDepartments();

    public Department findDepartmentById(Integer departmentId);

    public void addDepartment(Department department);

    public void updateDepartment(Department department);

    public void deleteDepartment(Integer departmentId);

    public int countDepartments();
}