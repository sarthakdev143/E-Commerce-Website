package com.example.ecomweb.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecomweb.Repos.UserRepository;

@Service
public class UserServices {
    @Autowired
    private UserRepository repo;
}
