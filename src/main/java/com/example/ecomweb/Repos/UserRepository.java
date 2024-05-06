package com.example.ecomweb.Repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ecomweb.Entity.UserBean;

@Repository
public interface UserRepository extends CrudRepository<UserBean, Integer> {

    UserBean findByEmail(String email);
}
