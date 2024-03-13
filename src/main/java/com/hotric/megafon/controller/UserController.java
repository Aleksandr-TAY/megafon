package com.hotric.megafon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {
    
    @GetMapping("/hello")
    public String userPage(Principal principal) {
        return "Hello " + principal.getName();
    }
    
    @GetMapping("/fe")
    public String frontendPage(Principal principal) {
        return "You are frontend developer, " + principal.getName();
    }
    
    @GetMapping("/be")
    public String backendPage(Principal principal) {
        return "You are backend developer, " + principal.getName();
    }
    
    @GetMapping("/admin")
    public String adminPage(Principal principal) {
        return "You are admin, " + principal.getName();
    }
}
