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
public class Teacher {
    private String teacherId;
    private String name;
    private String gender;
    private String title;
    private String fieldofStudy;
    private String contactNumber;
    private String departmentid;
}