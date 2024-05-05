package com.example.ecomweb.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ecomweb.Entity.CartBean;
import com.example.ecomweb.Entity.ProductsBean;
import com.example.ecomweb.Services.CartService;
import com.example.ecomweb.Services.ProductsService;


@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductsService productsService;

    @GetMapping("/cart")
    public String getAllCarts(Model model) {
        List<CartBean> cartBeans = cartService.findAllCarts();
        model.addAttribute("cartItems", cartBeans);
        return "User/cart";
    }

    @GetMapping("/addToCart/{name}")
    public String addToCart(@PathVariable("name") String name, Model model) {
        ProductsBean product = productsService.findByName(name);
        cartService.save(product);
        System.out.println("Product Saved");
        return "redirect:/viewProduct/{name}";
    }
}