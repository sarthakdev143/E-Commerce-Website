package com.example.ecomweb.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecomweb.Entity.CartItemBean;
import com.example.ecomweb.Repos.CartItemRepository;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    // Retrieve all cart items
    public List<CartItemBean> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    // Save a new Cart Item
    public CartItemBean saveCartItem(CartItemBean cartItemBean) {
        return cartItemRepository.save(cartItemBean);
    }

    // Delete a cart item by ID
    public void deleteCartItemById(Long id) {
        cartItemRepository.deleteById(id);
    }
}
