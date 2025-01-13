package com.cnlbc.controller;

import com.cnlbc.pojo.Department;
import com.cnlbc.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // 添加方法来处理获取部门数据的请求
    @RequestMapping("/getDepartments")
    @ResponseBody
    public List<Department> getDepartments() {
        return departmentService.findAllDepartments();
    }
}
