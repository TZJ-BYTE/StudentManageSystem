package com.cnlbc.pojo;

public class Course {
    private String courseId;
    private String courseName;
    private String description;
    private String departmentId;

    // 生成相应的Getter、Setter方法以及构造函数（这里省略）

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", description='" + description + '\'' +
                ", departmentId='" + departmentId + '\'' +
                '}';
    }
}