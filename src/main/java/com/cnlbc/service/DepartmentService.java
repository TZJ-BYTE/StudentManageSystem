package com.cnlbc.service;

import com.cnlbc.pojo.Department;

import java.util.List;

public interface DepartmentService {
    public List<Department> findAllDepartments();

    public Department findDepartmentById(Integer departmentId);

    public void addDepartment(Department department);

    public void updateDepartment(Department department);

    public void deleteDepartment(Integer departmentId);

    public int countDepartments();
}