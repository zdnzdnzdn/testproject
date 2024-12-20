package com.finalproject.client_app_etickpark.controller;

import java.io.IOException;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.finalproject.client_app_etickpark.entity.Wahana;
import com.finalproject.client_app_etickpark.model.request.WahanaRequest;
import com.finalproject.client_app_etickpark.service.WahanaService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/wahana")
@PreAuthorize("hasAny('ADMIN')")
public class WahanaController {
    private WahanaService wahanaService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("wahanas", wahanaService.getAll());
        model.addAttribute("isActive", "wahana");

        return "page/wahana/index";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id, Model model) {
        model.addAttribute("wahana", wahanaService.getById(id));
        model.addAttribute("isActive", "wahana");

        return "page/wahana/detail";
    }


    @GetMapping("/create")
    public String createView(Wahana wahana, Model model) {
        model.addAttribute("wahana", new Wahana());
        model.addAttribute("isActive", "wahana");

        return "page/wahana/create-form";
    }

    @PostMapping
    public String create(@ModelAttribute WahanaRequest wahanaRequest) throws IOException {
        wahanaService.create(wahanaRequest);
        return "redirect:/wahana";
    }


    @GetMapping("/update/{id}")
    public String updateView(@PathVariable Integer id, Wahana wahana, Model model) {
        model.addAttribute("wahana", wahanaService.getById(id));
        return "wahana/update-form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, Wahana wahana) {
        wahanaService.update(id, wahana);
        return "redirect:/wahana";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        wahanaService.delete(id);
        return "redirect:/wahana";
    }
}
