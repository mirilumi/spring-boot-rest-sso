package com.springbootrestsso.springbootrestsso.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String unProtected(){
        return "unsafe";
    }
    @GetMapping("/api")
    public String safe(){
        return "safe";
    }
}
