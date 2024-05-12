package com.example.ecomweb.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecomweb.Entity.CartBean;
import com.example.ecomweb.Entity.CartItemBean;
import com.example.ecomweb.Entity.ProductsBean;
import com.example.ecomweb.Repos.CartItemRepository;
import com.example.ecomweb.Repos.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public void addProductToCart(CartBean cart, ProductsBean productsBean) {
        CartItemBean cartItem = new CartItemBean();
        cartItem.setProduct(productsBean);
        cartItem.setQuantity(1);
        cartItem.setCart(cart);

        cart.getCartItems().add(cartItem);

        if (cart.getTotalPrice() == null) {
            cart.setTotalPrice(0L);
        }

        int totalPrice = Integer.parseInt(productsBean.getPrice().replaceAll("[^\\d]", ""));
        cart.setTotalPrice(cart.getTotalPrice() + totalPrice);

        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
    }
}