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
        System.out.println("username = " + username);
        return userRepository.findUserByName(username);
    }
}
