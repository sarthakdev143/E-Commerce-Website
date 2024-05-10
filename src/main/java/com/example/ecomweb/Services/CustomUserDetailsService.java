package com.example.ecomweb.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ecomweb.Entity.UserBean;
import com.example.ecomweb.Repos.UserRepository3;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository3 userRepository3;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        System.out.println("Entered Load User By Username");
        
        System.out.println("Username / Email : " + usernameOrEmail);
        UserBean user = userRepository3.findByEmail(usernameOrEmail);
        System.out.println("User : " + user);

        if (user != null) {
            System.out.println("Going to Next Method");
            return buildUserDetails(user);
        } else {
            System.out.println("Email : " + usernameOrEmail);
            System.out.println("Throwing Exception");
            throw new UsernameNotFoundException("Invalid email or password login failed");
        }
    }

    private UserDetails buildUserDetails(UserBean user) {
        System.out.println("Build User Detail");
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

        System.out.println("Returned User Detail");
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities);
    }
}