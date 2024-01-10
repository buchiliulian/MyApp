package com.example.myapp;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> userList;

    public UserService() {
        userList = new ArrayList<>();
    }

    public User checkUser(String uname, String password) {
        for (User user : userList) {
            if (user.getName().equals(uname) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        userList.add(user);
    }


    //按指定编号修改用户密码
    public void alterUser(User user, String password) {
        if (user != null) {
            user.setPassword(password);
        }
    }
}