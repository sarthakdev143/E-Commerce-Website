package com.example.ecomweb.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecomweb.Entity.CartBean;
import com.example.ecomweb.Repos.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    // Find a cart by ID
    public CartBean getCartById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    // Save a new cart
    public CartBean saveCart(CartBean cartBean) {
        return cartRepository.save(cartBean);
    }
}
