package com.example.ecomweb.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ecomweb.Entity.ProductsBean;
import com.example.ecomweb.Services.ProductsService;

@Controller
public class ExtraController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/")
    public String home(Model model) {
        System.out.println("\n\nEntered Home Page..");
        List<ProductsBean> products = productsService.getAllProducts();

        model.addAttribute("products", products);
        return "User/index";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "User/access-denied";
    }
}
