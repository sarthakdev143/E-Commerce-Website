package com.example.ecomweb.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecomweb.Entity.CartBean;
import com.example.ecomweb.Entity.UserBean;

public interface CartRepository extends JpaRepository<CartBean, Long> {
    CartBean findByUser(UserBean user);
}
