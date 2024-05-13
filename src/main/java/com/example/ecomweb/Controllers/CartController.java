package com.example.ecomweb.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.ecomweb.Entity.CartBean;
import com.example.ecomweb.Entity.CartItemBean;
import com.example.ecomweb.Entity.ProductsBean;
import com.example.ecomweb.Entity.UserBean;
import com.example.ecomweb.Repos.CartItemRepository;
import com.example.ecomweb.Repos.CartRepository;
import com.example.ecomweb.Services.CartService;
import com.example.ecomweb.Services.ProductsService;
import com.example.ecomweb.Services.UserServices;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductsService productsService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemRepository cartItemRepository;

    public CartBean CreateNewCart(UserBean userBean) {
        System.out.println("Creating New Cart For User : " + userBean.getName());
        CartBean cartBean = new CartBean();
        cartBean.setUser(userBean);
        return cartBean;
    }

    @GetMapping
    public String viewCart(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserBean userBean = userServices.findByEmail(email);
        CartBean cartBean = cartRepository.findByUser(userBean);
        System.out.println("\n\n\nLoading Cart For Use : " + userBean.getName());
        if (cartBean == null) {
            System.out.println("Cart Not Found");
            cartBean = CreateNewCart(userBean);
            cartRepository.save(cartBean);
        } else {
            System.out.println("Cart Found");
            List<CartItemBean> cartItems = cartItemRepository.findByCart(cartBean);
            if (cartItems.isEmpty()) {
                System.out.println("Cart Is Empty");
            } else {
                System.out.println("Found Items In Cart");
                for (CartItemBean cartItem : cartItems) {
                    ProductsBean product = cartItem.getProduct();
                    System.out.println("Product Name: " + product.getName());
                    System.out.println("Price: " + product.getPrice());
                    cartItem.setPrice(product.getPrice());
                }
            }
        }
        List<CartItemBean> cartItemBeans = cartItemRepository.findByCart(cartBean);
        model.addAttribute("cartItemList", cartItemBeans);
        return "User/cart";
    }

    @GetMapping("/add/{name}")
    public String addToCart(@PathVariable("name") String productName) {
        System.out.println("\n\n\nEntered The Product Adding Method");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserBean userBean = userServices.findByEmail(email);

        System.out.println("Name Of User, Who Requested The Product : " + userBean.getName());
        ProductsBean productsBean = productsService.findByName(productName);

        System.out.println("Finding Product : " + productName);

        if (productsBean != null) {
            System.out.println("Entered If Statement \nProduct Found");
            CartBean cart = cartRepository.findByUser(userBean);
            if (cart == null) {
                System.out.println("Cart Not Found");
                cart = CreateNewCart(userBean);
                cartRepository.save(cart);
            }

            cartService.addProductToCart(cart, productsBean);
            System.out.println("Product : " + productName + " - Has Been Successfully Added To Cart");

            return "redirect:/cart";
        } else {
            System.out.println("Entered Else Statement \nProduct Not Found");
            return "redirect:/error";
        }
    }

}