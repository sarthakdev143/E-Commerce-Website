package com.example.ecomweb.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomLogoutHandler customLogoutHandler;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests

                        .requestMatchers("/sign-up/**").permitAll()
                        .requestMatchers("/login/**").permitAll()
                        .requestMatchers("/cart/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers("/admin-panel/**").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated())

                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .permitAll())

                .logout(obj -> obj.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .addLogoutHandler(customLogoutHandler)
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true).deleteCookies("JESSIONID"));
        http.exceptionHandling((hi) -> hi.accessDeniedPage("/access-denied"));
        System.out.println("\n\nHello from Security Config\n\n");
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/", "/images/{name}", "/make-up/**", "/viewProduct/{name}",
                "/saveAccount", "/verifyEmail");
    }
}
