package com.example.ecomweb.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ecomweb.DTOs.ProductsDto;
import com.example.ecomweb.Entity.ProductsBean;
import com.example.ecomweb.Entity.UserBean;
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

    @GetMapping("/userList")
    public String getUsers(Model model) {
        List<UserBean> users = userServices.getAllUsers();
        model.addAttribute("users", users);
        return "Admin/users";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userServices.deleteUser(id);
        return "redirect:/userList";
    }

    // Temp.
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
