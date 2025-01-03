package com.cnlbc.repository;

import com.cnlbc.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TeacherMapper {

    @Select("SELECT teacherId, name, gender, title FROM teachers LIMIT #{offset}, #{pageSize}")
    List<Teacher> selectTeachers(@Param("offset") int offset, @Param("pageSize") int pageSize);
}