package com.duoc.week3.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.duoc.week3.model.User;
import com.duoc.week3.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository = new UserRepository();

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

}
