package com.cnlbc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private Integer teacherId;
    private String name;
    private String gender;
    private String title;
    private String fieldOfStudy;
    private String contactNumber;
    private Integer departmentId;
    private String departmentName;
    private String address;
    private String classId; // 修改为 String 类型
    private String className;
}
