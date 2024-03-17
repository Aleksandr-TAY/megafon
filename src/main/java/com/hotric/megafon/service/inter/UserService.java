package com.hotric.megafon.service.inter;

import com.hotric.megafon.models.UserEntity;

import java.security.Principal;

public interface UserService {
    void addUser(UserEntity user);
    
    UserEntity findByUsername(String username);
    
    boolean validateUser(String name);
    
    boolean validateUserByRoles(Principal principal);
}
