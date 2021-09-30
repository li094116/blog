package com.lyx.service;

import com.lyx.pojo.User;

public interface UserService {
    User checkUser(String userName, String password);
}
