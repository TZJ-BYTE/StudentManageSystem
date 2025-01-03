package com.cnlbc.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
/*等同于各个字段的get()和set()方法*/
@NoArgsConstructor
/*等同于无参构造*/
@AllArgsConstructor
/*等同于全参构造*/
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