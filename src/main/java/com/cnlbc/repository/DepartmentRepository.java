package com.cnlbc.repository;

import com.cnlbc.pojo.Course;
import com.cnlbc.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DepartmentRepository {
    public List<Department> findAllDepartments();

    public Department findDepartmentById(Integer departmentId);

    public void addDepartment(Department department);

    public void updateDepartment(Department department);

    public void deleteDepartment(Integer departmentId);

    public int countDepartments();
    List<Department> findDepartmentByIdOrName(@Param("searchTerm") String searchTerm,
                                      @Param("offset") int offset,
                                      @Param("pageSize") int pageSize);

    int countDepartmentsByIdOrName(@Param("searchTerm") String searchTerm);
}
