package com.example.ecomweb.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecomweb.Entity.UserBean;
import com.example.ecomweb.Repos.UserRepository;
import com.example.ecomweb.Repos.UserRepository2;

@Service
public class UserServices {
    @Autowired
    private UserRepository repo;

    @Autowired
    private UserRepository2 repo2;

    public UserBean saveUser(UserBean user) {
        user.setId(null);
        return repo.save(user);
    }

    // Find User By Email
    public Optional<UserBean> findUserByEmail(String email) {
        Optional<UserBean> users = repo2.findByEmail(email);
        return users;
    }

    public List<UserBean> getAllUsers() {
        List<UserBean> listOfUsers = (List<UserBean>) repo.findAll();
        return listOfUsers;
    }

    public void deleteUser(Integer id) {
        repo.deleteById(id);
    }
}
