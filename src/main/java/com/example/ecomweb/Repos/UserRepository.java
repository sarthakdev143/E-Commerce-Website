package com.example.ecomweb.Repos;

import org.springframework.data.repository.CrudRepository;

import com.example.ecomweb.Entity.UserBean;

public interface UserRepository extends CrudRepository<UserBean, Integer> {
}
