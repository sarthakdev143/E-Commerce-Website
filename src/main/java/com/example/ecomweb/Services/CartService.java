package com.example.ecomweb.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecomweb.Entity.CartBean;
import com.example.ecomweb.Entity.ProductsBean;
import com.example.ecomweb.Repos.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<CartBean> findAllCarts() {
        return cartRepository.findAll();
    }

    public void save(ProductsBean product) {
        cartRepository.save(product);
    }

    public void delete(int id) {
        cartRepository.deleteById(id);
    }
}
