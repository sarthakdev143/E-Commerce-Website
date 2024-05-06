package com.example.ecomweb.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.ecomweb.Services.CartItemService;

@Controller
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;
}
