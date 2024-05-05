package com.example.ecomweb.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecomweb.Entity.CartBean;
import com.example.ecomweb.Services.CartService;


@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public String getAllCarts(Model model) {
        // List<CartBean> cartBeans = cartService.findAllCarts();
        // model.addAttribute("cartItems", cartBeans);
        return "User/cart";
    }
}