package com.hotric.megafon.controller;

import com.hotric.megafon.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;

@RestController
public class UserController {
    private UserService userService;
    private UserDetailsService userDetailsService;
    
    public UserController(UserService userService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }
    
    @GetMapping("/hello")
    public String userPage(Principal principal) {
        return "Hello " + principal.getName();
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN') or #username == authentication.name")
    @GetMapping("/hello/{username}")
    public ResponseEntity<String> personalPage(@PathVariable("username") String username, Principal principal) {
        
        Collection<? extends GrantedAuthority> authorities = userDetailsService.loadUserByUsername(principal.getName()).getAuthorities();
        
        if (username.equals(principal.getName())) {
            return ResponseEntity.ok().body("This is your personal page, " + principal.getName());
        }
        
        for (GrantedAuthority role : authorities) {
            if (role.getAuthority().contains("ROLE_ADMIN")) {
                return ResponseEntity.ok().body("This is your personal page, " + username);
            } else {
                return ResponseEntity.status(404).build();
            }
        }
        return ResponseEntity.status(403).build();
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
