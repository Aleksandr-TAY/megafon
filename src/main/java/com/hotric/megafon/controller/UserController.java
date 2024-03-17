package com.hotric.megafon.controller;

import com.hotric.megafon.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static com.hotric.megafon.common.Constants.HELLO;
import static com.hotric.megafon.common.Constants.PAGE;
import static com.hotric.megafon.common.Constants.FRONTEND_TEXT;
import static com.hotric.megafon.common.Constants.BACKEND_TEXT;
import static com.hotric.megafon.common.Constants.ADMIN_TEXT;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    
    @GetMapping("/hello")
    public String userPage(Principal principal) {
        return HELLO + principal.getName();
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN') or #username == authentication.name")
    @GetMapping("/hello/{username}")
    public ResponseEntity<String> personalPage(@PathVariable("username") String username, Principal principal) {
        if(userService.validateUser(username)){
            return ResponseEntity.status(404).build();
        }
        if (username.equals(principal.getName())) {
            return ResponseEntity.ok().body(PAGE + principal.getName());
        }
        return userService.validateUserByRoles(principal) ?
                ResponseEntity.ok().body(PAGE + username)
                : ResponseEntity.status(403).build();
    }
    
    @GetMapping("/fe")
    public String frontendPage(Principal principal) {
        return FRONTEND_TEXT + principal.getName();
    }
    
    @GetMapping("/be")
    public String backendPage(Principal principal) {
        return BACKEND_TEXT + principal.getName();
    }
    
    @GetMapping("/admin")
    public String adminPage(Principal principal) {
        return ADMIN_TEXT + principal.getName();
    }
}
