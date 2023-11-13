package com.sohu.service;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl01 implements UserService {
    @Override
    public void test() {
        System.out.println("UserServiceImpl01");
    }
}
