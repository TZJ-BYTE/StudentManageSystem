package com.cnlbc.repository;

import com.cnlbc.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRepository {
    public List<User> findAllUser();
    public List<User> findUserByName(String username);
    public void updateUserName(@Param("usertid") int usertid, @Param("newvalue") String newvalue);
    public void updatePassword(@Param("usertid") int usertid, @Param("password") String password);
    public List<User>  findByPasswd (String username,String password);
    public void insertUser(String username,String password);
    public boolean registerUser(User user);
    public Integer getMaxUsertId();
}
