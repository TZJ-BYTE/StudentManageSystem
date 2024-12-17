package com.cnlbc.repository;

import com.cnlbc.pojo.User;

import java.util.List;

public interface UserRepository {
    public List<User> findAllUser();
}