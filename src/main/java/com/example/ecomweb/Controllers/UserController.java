package com.example.ecomweb.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.ecomweb.Services.UserServices;


@Controller
public class UserController {
    @SuppressWarnings("unused")
    @Autowired
    private UserServices services;

}
