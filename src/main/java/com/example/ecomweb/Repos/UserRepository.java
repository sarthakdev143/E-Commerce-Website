package com.example.ecomweb.Repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ecomweb.Entity.UserBean;

@Repository
public interface UserRepository extends CrudRepository<UserBean, Integer> {

    List<UserBean> findByEmail(String email);

    UserBean findByName(String username);
}
