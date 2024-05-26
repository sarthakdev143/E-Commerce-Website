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
        System.out.println("Entered 'Add Product to Cart' Method Of Service");
        CartItemBean existingCartItem = cartItemRepository.findByCartAndProduct(cart,
                productsBean);
        System.out.println(
                "Executing Process For Adding '" + productsBean.getName() + "' to Cart of '"
                        + cart.getUser().getName()
                        + "'");

        if (existingCartItem != null) {
            System.out.println("Entered If Statement");
            existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
            cartItemRepository.save(existingCartItem);
        } else {
            System.out.println("Entered Else Statement");
            CartItemBean cartItem = new CartItemBean();
            cartItem.setProduct(productsBean);
            cartItem.setQuantity(1);
            cartItem.setCart(cart);
            cartItem.setPrice(productsBean.getPrice());
            cart.getCartItems().add(cartItem);
            System.out.println("New cart item added to cart");
            cartItemRepository.save(cartItem);
            cartRepository.save(cart);
        }

        System.out.println("Updating Price of Cart..");
        int totalPrice = Integer.parseInt(productsBean.getPrice().replaceAll("[^\\d]", ""));
        cart.setTotalPrice(cart.getTotalPrice() + totalPrice);
        System.out.println("Total Price Of Cart : ");

        updateTotalPrice(cart);
        System.out.println("Total Cart price updated : " + cart.getTotalPrice() +
                totalPrice);
    }

    private void updateTotalPrice(CartBean cart) {
        System.out.println("Entered Update Total Price Method..");
        Long totalPrice = 0L;
        List<CartItemBean> cartItems = cart.getCartItems();
        for (CartItemBean cartItem : cartItems) {
            int productPrice = Integer.parseInt(cartItem.getProduct().getPrice().replaceAll("[^\\d]", ""));
            totalPrice += productPrice * cartItem.getQuantity();
        }
        cart.setTotalPrice(totalPrice);
        System.out.println("Saving Cart Of : " + cart.getUser().getName() + " with total price of " + totalPrice);
        cartRepository.save(cart);
    }

    public void updateCartItemQuantity(CartItemBean cartItemBean, int quantity) {
        System.out.println(
                "Changing The Quantity Of " + cartItemBean.getProduct().getName() + " to " +
                        quantity + " in the Cart");
        cartItemBean.setQuantity(quantity);
        cartItemRepository.save(cartItemBean);
    }

    public void removeCartItem(CartBean cart, ProductsBean productsBean) {
        System.out.println("Entered Remove Cart Item Method in Service..");
        CartItemBean cartItemBean = cartItemRepository.findByCartAndProduct(cart, productsBean);
        System.out.println("Deleting Cart Item : " + cartItemBean.getProduct().getName());
        cartItemRepository.delete(cartItemBean);
    }
}
