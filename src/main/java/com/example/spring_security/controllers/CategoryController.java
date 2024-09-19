package com.example.spring_security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @GetMapping
    public String getCategory() {
        return "Category Controller";
    }

    @GetMapping("/new")
    public String login() {
        return "Login category";
    }
}
