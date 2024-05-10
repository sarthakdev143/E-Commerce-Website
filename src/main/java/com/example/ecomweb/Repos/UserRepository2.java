package com.example.ecomweb.Repos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ecomweb.Entity.UserBean;

@Repository
public interface UserRepository2 extends CrudRepository<UserBean, Integer> {

    Optional<UserBean> findByEmail(String email);
}