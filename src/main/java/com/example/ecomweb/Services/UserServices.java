package com.example.ecomweb.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
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
    public List<UserBean> findUserByEmail(String email) {
        List<UserBean> users = repo.findByEmail(email);
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
