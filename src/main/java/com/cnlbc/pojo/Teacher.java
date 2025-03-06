package com.cnlbc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private Integer teacherId; // 修改为Integer
    private String name;
    private String gender;
    private String title;
    private String fieldOfStudy; // 修改为fieldOfStudy
    private String contactNumber;
    private Integer departmentId; // 修改为Integer
}
