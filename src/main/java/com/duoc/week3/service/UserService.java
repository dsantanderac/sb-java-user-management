package com.duoc.week3.service;

import java.util.List;
import com.duoc.week3.model.User;

// Service layer for User
public interface UserService {
    List<User> getAllUsers();

    User getUserById(int id);
}
