package com.example.ecomweb.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.ecomweb.Entity.CartBean;
import com.example.ecomweb.Entity.ProductsBean;
import com.example.ecomweb.Entity.UserBean;
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

    @GetMapping
    public String viewCart(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserBean userBean = userServices.findByEmail(email);
        CartBean cartBean = cartRepository.findByUser(userBean);
        model.addAttribute("cart", cartBean);
        return "User/cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam("productName") String productName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserBean userBean = userServices.findByEmail(email);

        ProductsBean productsBean = productsService.findByName(productName);

        if (productsBean != null) {
            CartBean cart = cartRepository.findByUser(userBean);

            // Assuming you have a method to add a product to the cart in CartService
            cartService.addProductToCart(cart, productsBean);

            return "redirect:/cart"; 
        } else {
            return "redirect:/error";
        }
    }

}