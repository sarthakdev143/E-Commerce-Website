package com.example.ecomweb.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ecomweb.DTOs.ProductsDto;
import com.example.ecomweb.Entity.ProductsBean;
import com.example.ecomweb.Services.ProductsService;

@Controller
public class ExtraController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/")
    public String home(Model model) {
        List<ProductsBean> products = productsService.getAllProducts();
        model.addAttribute("products", products);
        return "User/index";
    }

    @GetMapping("/admin-panel")
    public String adminHome(Model model) {
        ProductsDto newProduct = new ProductsDto();
        model.addAttribute("newProduct", newProduct);

        // Check for a flash attribute containing a success message
        String successMessage = (String) model.asMap().get("successMessage");
        if (successMessage != null) {
            model.addAttribute("successMessage", successMessage);
        }

        return "Admin/admin-panel";
    }

    // Temp.
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
