package com.cnlbc.service;

import com.cnlbc.pojo.User;
import com.cnlbc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUser() {
        return userRepository.findAllUser();
    }

    @Override
    public List<User> findUserByName(String username) {
        return userRepository.findUserByName(username);
    }

    @Override
    public void registerUser(User user) {
        userRepository.registerUser(user);
    }

    @Override
    public Integer getMaxUsertId() {
        return userRepository.getMaxUsertId();
    }

    @Override
    public  void updateUserName(int usertid, String newvalue) {
        userRepository.updateUserName(usertid, newvalue);
    }
    @Override
    public void updatePassword(int usertid,String password){
        userRepository.updatePassword(usertid,password);
    }
    @Override
    public List<User>  findByPasswd (String username,String password){
        return userRepository.findByPasswd(username,password);
    }
    @Override
    public void insertUser(String username,String password){
        userRepository.insertUser(username,password);
    }

}
