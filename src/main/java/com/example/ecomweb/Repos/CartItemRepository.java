package com.example.ecomweb.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecomweb.Entity.CartItemBean;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemBean, Long> {
    
}
