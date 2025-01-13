package com.cnlbc.service;

import com.cnlbc.pojo.User;

import java.util.List;

public interface UserService {
    public List<User> findAllUser();
    public List<User> findUserByName(String username);
    public void updateUserName(String oldname,String newvalue);
    public void updatePassword(String username,String password);
    public List<User>  findByPasswd (String username,String password);
    public void insertUser(String username,String password);
}
