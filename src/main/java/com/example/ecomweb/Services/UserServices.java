package com.example.ecomweb.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecomweb.Entity.UserBean;
import com.example.ecomweb.Repos.UserRepository;

@Service
public class UserServices {
    @Autowired
    private UserRepository repo;

    public UserBean saveUser(UserBean user) {
        return repo.save(user);
    }

    // Find User By Email
    public UserBean findUserByEmail(String email) {
        return repo.findByEmail(email);
    }
}
