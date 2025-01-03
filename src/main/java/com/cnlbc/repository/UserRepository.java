package com.cnlbc.repository;

import com.cnlbc.pojo.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserRepository {
    public List<User> findAllUser();
    public List<User> findUserByName(String username);
}

