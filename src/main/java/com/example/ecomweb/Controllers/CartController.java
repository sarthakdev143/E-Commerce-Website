package com.example.ecomweb.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.ecomweb.Services.CartItemService;
import com.example.ecomweb.Services.CartService;


@Controller
public class CartController {
    @SuppressWarnings("unused")
    @Autowired
    private CartService cartService;

    @SuppressWarnings("unused")
    @Autowired
    private CartItemService cartItemService;

}