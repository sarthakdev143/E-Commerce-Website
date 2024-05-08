package com.example.ecomweb.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ecomweb.Entity.UserBean;
import com.example.ecomweb.Services.EmailService;
import com.example.ecomweb.Services.UserServices;

import jakarta.mail.MessagingException;

@Controller
public class UserController {
    @Autowired
    private UserServices userServices;

    @Autowired
    private EmailService emailService;

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
        List<UserBean> existingUserOpt = userServices.findUserByEmail(userBean.getEmail());

        System.out.println("List = " + existingUserOpt);
        if (existingUserOpt == null) {
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

    @PostMapping("verifyEmail")
    public String verifyEmail(@ModelAttribute("accountObj") UserBean userBean, Model model,
            RedirectAttributes redirectAttributes) {
        if (genratedOtp.equals(userBean.getVerificationCode())) {
            userBean.setLoggedin(false);
            userServices.saveUser(userBean);
            redirectAttributes.addFlashAttribute("message",
                    "Successfully Created Account, You May Log in To Your Account Now");
            return "redirect:/login";
        } else {
            model.addAttribute("message", "OTP is incorrect! Pls Check The OTP You Have Entered");
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
