package com.example.ecomweb.Controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ecomweb.Entity.ProductsBean;
import com.example.ecomweb.Entity.Role;
import com.example.ecomweb.Entity.UserBean;
import com.example.ecomweb.Repos.ProductsRepository;
import com.example.ecomweb.Repos.RoleRepository;
import com.example.ecomweb.Security.TbConstants;
import com.example.ecomweb.Services.EmailService;
import com.example.ecomweb.Services.ProductsService;
import com.example.ecomweb.Services.UserServices;

import jakarta.mail.MessagingException;

@Controller
public class UserLogupController {
    @Autowired
    private UserServices userServices;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ProductsService productsService;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;
    

    @GetMapping("/images/{name}")
    public ResponseEntity<Resource> getImage(@PathVariable("name") String name) {
        Optional<ProductsBean> fileOptional = Optional.ofNullable(productsRepository.findByName(name));
        if (fileOptional.isPresent()) {
            ProductsBean productsBean = fileOptional.get();

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + productsBean.getName() + "\"")
                    .body(new ByteArrayResource(productsBean.getImage()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/viewProduct/{name}")
    public String viewProduct(@PathVariable("name") String productName, Model model) {
        ProductsBean productData = productsService.findByName(productName);
        model.addAttribute("product", productData);
        return "User/view-product";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("message") String message, Model model) {
        String message2 = "Successfully Created Account, You May Log in To Your Account Now";
        if (message != message2) {
            message = null;
        }
        model.addAttribute("message", message);
        return "User/login";
    }

    @GetMapping("/sign-up")
    public String getSignUp(Model model) {
        UserBean userBean = new UserBean();
        model.addAttribute("accountObj", userBean);
        return "User/sign-up";
    }

    private String genratedOtp;

    @PostMapping("/saveAccount")
    public String saveAccount(@ModelAttribute("accountObj") UserBean userBean, Model model,
            RedirectAttributes redirectAttributes) {
        Optional<UserBean> existingUserOpt = userServices.findUserByEmail(userBean.getEmail());

        if (existingUserOpt.isPresent()) {
            model.addAttribute("message", "Email is already registered");
            return "User/sign-up";
        } else {
            genratedOtp = generateOtp();

            // Send OTP email
            try {
                System.out.println("\nInside Try");
                emailService.sendOtpEmail(userBean.getEmail(), genratedOtp);
            } catch (MessagingException e) {
                System.out.println("\nPrinting Exception");
                e.printStackTrace();
                model.addAttribute("message", "Error sending email. Please try again later.");
                return "User/sign-up";
            }

            model.addAttribute("accountObj", userBean);
            return "User/verify-email";
        }
    }

    @PostMapping("/verifyEmail")
    public String verifyEmail(@ModelAttribute("accountObj") UserBean userBean, Model model,
            RedirectAttributes redirectAttributes) {
        if (genratedOtp.equals(userBean.getVerificationCode())) {

            System.out.println("\n\nUser Credentials :- \nName : " + userBean.getName() + "\nEmail : "
                    + userBean.getEmail() + "\nPassword : " + userBean.getPassword());

            // Find the user role
            Optional<Role> userRoleOptional = roleRepository.findById(23l);
            Set<Role> userRoles = new HashSet<>();

            // Check if the role is present
            if (userRoleOptional.isPresent()) {
                userRoles.add(userRoleOptional.get());
            } else {
                // Create the user role if not present
                Role userRole = new Role();
                userRole.setId(TbConstants.Roles.USER);
                userRole.setName("ROLE_USER");

                // Save the new role to the repository
                roleRepository.save(userRole);
                userRoles.add(userRole);
            }

            // Add roles to the user and save the user
            userBean.setRoles(userRoles);

            // Hash the password before saving
            userBean.setPassword(passwordEncoder.encode(userBean.getPassword()));

            System.out.println("\n\nUser Credentials :- \nName : " + userBean.getName() + "\nEmail : "
                    + userBean.getEmail() + "\nPassword : " + userBean.getPassword() + "\nRoles : " + userBean.getRoles());

            System.out.println("Saving User");
            
            userServices.saveUser(userBean);

            redirectAttributes.addFlashAttribute("message",
                    "Successfully Created Account, You May Log in To Your Account Now");
            return "redirect:/login";
        } else {
            model.addAttribute("message", "OTP is incorrect! Please check the OTP you have entered.");
            return "User/verify-email";
        }
    }

    private String generateOtp() {
        // 6 Digit OTP
        String genratedOtp = String.valueOf(new Random().nextInt(900000) + 100000);
        System.out.println("\n\nOTP : " + genratedOtp);
        return genratedOtp;
    }
}
