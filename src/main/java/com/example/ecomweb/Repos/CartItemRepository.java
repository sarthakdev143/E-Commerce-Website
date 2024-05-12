package com.example.ecomweb.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecomweb.Entity.CartItemBean;

public interface CartItemRepository extends JpaRepository<CartItemBean, Long> {
    
}
