package com.cnlbc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Msg {
    private Boolean success;
    private String message;
    private Map<String, Object> data;

    // 添加一个构造函数来初始化默认值
    public Msg(Boolean success, String message) {
        this.success = success;
        this.message = message;
        this.data = new HashMap<>();
    }
}
