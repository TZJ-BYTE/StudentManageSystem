package com.cnlbc.controller;

import com.cnlbc.pojo.Department;
import com.cnlbc.pojo.Msg;
import com.cnlbc.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/depart")
public class DepartController {
    @Autowired
    public DepartmentService departmentService;
    @GetMapping("/search")
    public Msg findDepartmentByIdOrName(
            @RequestParam(value = "searchTerm") String searchTerm,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        // 获取分页结果
        List<Department> departments = departmentService.findDepartmentByIdOrName(searchTerm, page, pageSize);
        // 计算总页数
        int totalDepartments = departmentService.countDepartmentsByIdOrName(searchTerm);
        int totalPages = (int) Math.ceil((double) totalDepartments / pageSize);

        Map<String, Object> data = new HashMap<>();
        data.put("departments", departments);
        data.put("currentPage", page);
        data.put("pageSize", pageSize);
        data.put("totalPages", totalPages);

        // 封装到 Msg 对象中
        Msg msg = new Msg();
        msg.setSuccess(true);
        msg.setMessage("查询成功");
        msg.setData(data);
        System.out.println("学院返回的结果是"+msg);
        return msg;
    }
}
