package com.finalproject.client_app_etickpark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.finalproject.client_app_etickpark.service.WahanaService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
    
    private WahanaService wahanaService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("wahanas", this.wahanaService.getAll());
        model.addAttribute("isActive", "home");

        return "page/wahana";
    }
    
    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id, Model model) {
        model.addAttribute("wahana", this.wahanaService.getById(id));
        model.addAttribute("isActive", "home");
        
        return "page/detail wahana";
    }
    
}
