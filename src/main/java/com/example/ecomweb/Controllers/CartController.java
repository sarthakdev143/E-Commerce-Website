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
        System.out.println("Entered The Product Adding Method");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserBean userBean = userServices.findByEmail(email);
        
        System.out.println("Name Of User, Who Requested The Product : " + userBean.getName());
        ProductsBean productsBean = productsService.findByName(productName);

        System.out.println("Finding Product : " + productName);

        if (productsBean != null) {
            System.out.println("Entered If Statement \nProduct Found");
            CartBean cart = cartRepository.findByUser(userBean);
            
            cartService.addProductToCart(cart, productsBean);
            
            return "redirect:/cart"; 
        } else {
            System.out.println("Entered Else Statement \nProduct Not Found");
            return "redirect:/error";
        }
    }

}