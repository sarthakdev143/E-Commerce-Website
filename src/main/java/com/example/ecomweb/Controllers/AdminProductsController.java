package com.example.ecomweb.Controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ecomweb.DTOs.ProductsDto;
import com.example.ecomweb.Entity.ProductsBean;
import com.example.ecomweb.Entity.UserBean;
import com.example.ecomweb.Services.ProductsService;
import com.example.ecomweb.Services.UserServices;

// ## THIS CONTROLLER IS ESPECIALLY DESIGNED FOR ADMIN PANEL ## //

@Controller
@RequestMapping("/admin-panel")
public class AdminProductsController {
    @Autowired
    private ProductsService productsService;

    @Autowired
    private UserServices userServices;

    // ## 2 EXCLUSIVE METHODs, NOT RELATED TO PRODUCTS ## //
    @GetMapping("/userList")
    public String getUsers(Model model) {
        List<UserBean> users = userServices.getAllUsers();
        model.addAttribute("users", users);
        return "Admin/users";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userServices.deleteUser(id);
        return "redirect:/admin-panel/userList";
    }

    // ## ## //

    @GetMapping
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

    @PostMapping("/saveProduct")
    public String newProduct(@ModelAttribute("newProduct") ProductsBean newProduct,
            RedirectAttributes redirectAttributes, @RequestParam("productImg") MultipartFile file) throws IOException {

        newProduct.setImage(file.getBytes());
        productsService.save(newProduct);

        // Add a flash attribute for the success message
        redirectAttributes.addFlashAttribute("successMessage", "Product added successfully!");
        return "redirect:/admin-panel";
    }

    @GetMapping("/products")
    public String products(Model model) {
        List<ProductsBean> products = productsService.getAllProducts();
        model.addAttribute("products", products);
        return "Admin/products";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productsService.deleteById(id);
        return "redirect:/admin-panel/products";
    }

    @GetMapping("/editProduct/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        ProductsBean productsBean = productsService.findById(id);
        model.addAttribute("productBean", productsBean);
        return "Admin/edit-product";
    }

    @PostMapping("/updateProduct")
    public String editProduct(@ModelAttribute("newProduct") ProductsBean newProduct,
            @RequestParam("productImg") MultipartFile file) throws IOException {
        newProduct.setImage(file.getBytes());
        productsService.save(newProduct);
        return "redirect:/admin-panel/products";
    }
}
