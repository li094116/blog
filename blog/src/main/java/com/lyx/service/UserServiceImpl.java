package com.lyx.service;

import com.lyx.dao.UserRepository;
import com.lyx.pojo.User;
import com.lyx.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    /*注入*/
    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String userName, String password) {
        User user = userRepository.findByUserNameAndPassword(userName, MD5Util.getMd5(password));
        return user;
    }
}
