package com.hotric.megafon.controller;

import com.hotric.megafon.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {
    
    @GetMapping("/hello")
    public String userPage(Principal principal) {
        return "Hello " + principal.getName();
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN') or #username == authentication.name")
    @GetMapping("/hello/{username}")
    public ResponseEntity<String> personalPage(@PathVariable("username") String username, Principal principal) {
        
        if (username.equals(principal.getName())) {
            return ResponseEntity.ok().body("This is your personal page, " + principal.getName());
        } else {
            return ResponseEntity.status(403).build();
        }
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
