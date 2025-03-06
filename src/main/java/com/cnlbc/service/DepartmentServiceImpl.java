package com.cnlbc.service;

import com.cnlbc.pojo.Course;
import com.cnlbc.pojo.Department;
import com.cnlbc.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAllDepartments() {
        System.out.println("findAllDepartments" + departmentRepository.findAllDepartments());
        return departmentRepository.findAllDepartments();
    }

    @Override
    public Department findDepartmentById(Integer departmentId) {
        return departmentRepository.findDepartmentById(departmentId);
    }

    @Override
    public void addDepartment(Department department) {
        departmentRepository.addDepartment(department);
    }

    @Override
    public void updateDepartment(Department department) {
        departmentRepository.updateDepartment(department);
    }

    @Override
    public void deleteDepartment(Integer departmentId) {
        departmentRepository.deleteDepartment(departmentId);
    }

    @Override
    public int countDepartments() {
        return departmentRepository.countDepartments();
    }
    @Override
    public List<Department> findDepartmentByIdOrName(String searchTerm, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return departmentRepository.findDepartmentByIdOrName(searchTerm, offset, pageSize);
    }

    @Override
    public int countDepartmentsByIdOrName(String searchTerm) {
        return departmentRepository.countDepartmentsByIdOrName(searchTerm);
    }
}