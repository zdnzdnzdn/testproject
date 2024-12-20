package com.finalproject.client_app_etickpark.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.finalproject.client_app_etickpark.entity.Customer;
import com.finalproject.client_app_etickpark.model.request.LoginRequest;
import com.finalproject.client_app_etickpark.model.request.RegisterRequest;
import com.finalproject.client_app_etickpark.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping
public class AuthController {
    @Autowired
    private AuthService authService;

    // Display Method
    @GetMapping("/auth/login")
    public String displayLoginPage(
        LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response, Model model, Principal principal
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            log.info("User {} successfully logged in.", authentication.getName());

            return "redirect:/";
        }

        log.info("Displaying login page.");

        model.addAttribute("login", new LoginRequest());

        return "page/auth/login";
    }

    @GetMapping("/register")
    public String displayRegisterPage(Model model) {
        model.addAttribute("register", new RegisterRequest());
        return "page/auth/register";
    }

    // Process Method
    @PostMapping("/login")
    public String processLogin(
        LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response
    ) {
        boolean isAuthenticated = authService.login(loginRequest, request, response);

        if (!isAuthenticated) {
            log.warn("Login attempt failed for username: {}", loginRequest.getUsername());
            return "redirect:/auth/login?error=true";
        }

        return "redirect:/";
    }

    @PostMapping("register")
    public String registration(RegisterRequest registerRequest) {
        Customer response = authService.registration(registerRequest);
        log.info("Registration response: {}", response);

        return "redirect:/auth/login";
    }
}
