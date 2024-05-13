package com.example.ecomweb.Services;

import java.util.List;

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
        // Check if the product already exists in the cart
        CartItemBean existingCartItem = cartItemRepository.findByCartAndProduct(cart, productsBean);

        if (existingCartItem != null) {
            // If the product exists, update the quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
            cartItemRepository.save(existingCartItem);
        } else {
            // If the product does not exist, create a new CartItemBean
            CartItemBean cartItem = new CartItemBean();
            cartItem.setProduct(productsBean);
            cartItem.setQuantity(1);
            cartItem.setCart(cart);

            // Calculate price and set it to cartItem
            String productPrice = productsBean.getPrice();
            cartItem.setPrice(productPrice);

            cart.getCartItems().add(cartItem);
            cartItemRepository.save(cartItem);
        }

        // Update total price of the cart
        int totalPrice = Integer.parseInt(productsBean.getPrice().replaceAll("[^\\d]", ""));
        cart.setTotalPrice(cart.getTotalPrice() + totalPrice);

        // Update Total Price Of Cart
        updateTotalPrice(cart);
    }

    private void updateTotalPrice(CartBean cart) {
        Long totalPrice = 0L;
        List<CartItemBean> cartItems = cart.getCartItems();
        for (CartItemBean cartItem : cartItems) {
            int productPrice = Integer.parseInt(cartItem.getProduct().getPrice().replaceAll("[^\\d]", ""));
            totalPrice += productPrice * cartItem.getQuantity();
        }
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);
    }
}
