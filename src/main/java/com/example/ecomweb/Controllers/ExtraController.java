package com.example.ecomweb.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ecomweb.Entity.ProductsBean;
import com.example.ecomweb.Services.ProductsService;
import com.example.ecomweb.Services.UserServices;

@Controller
public class ExtraController {

    @Autowired
    private ProductsService productsService;

    @Autowired
    private UserServices userServices;

    @GetMapping("/")
    public String home(Model model) {
        List<ProductsBean> products = productsService.getAllProducts();
        model.addAttribute("products", products);
        return "User/index";
    }

    // Temp.
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
