package com.example.ecomweb.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ecomweb.Entity.CartItemBean;
import com.example.ecomweb.Entity.UserBean;
import com.example.ecomweb.Services.CartItemService;
import com.example.ecomweb.Services.UserServices;

@Controller
public class UserController {
    @Autowired
    private UserServices userServices;

    @GetMapping("/sign-up")
    public String getSignUp(Model model) {
        UserBean userBean = new UserBean();
        model.addAttribute("accountObj", userBean);
        return "User/sign-up";
    }

    @PostMapping("/saveAccount")
    public String saveAccount(@ModelAttribute("accountObj") UserBean userBean, Model model) {
        UserBean existingUser = userServices.findUserByEmail(userBean.getEmail());

        if (existingUser != null) {
            System.out.println("Account already exists");
            model.addAttribute("message", "Email is Already Registered");
            return "User/sign-up";
        } else {
            userServices.saveUser(userBean);
            System.out.println("Account Saved");
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        
        return "User/login";
    }
}
