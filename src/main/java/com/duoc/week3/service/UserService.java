package com.duoc.week3.service;

import java.util.List;
import com.duoc.week3.model.User;

// Service layer for User
public interface UserService {
    List<User> getAll();

    User getById(int id);
}
