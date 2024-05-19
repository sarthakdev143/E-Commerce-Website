package com.example.ecomweb.Security;

import com.example.ecomweb.Entity.UserBean;
import com.example.ecomweb.Repos.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLogoutHandler implements LogoutHandler {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserBean) {
            UserBean user = (UserBean) authentication.getPrincipal();
            user.setLoggedIn(false);
            System.out.println(user.getName() + " has been Successfully Logged Out..");
            userRepository.save(user);
        }
    }
}
