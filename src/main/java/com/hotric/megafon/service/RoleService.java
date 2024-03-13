package com.hotric.megafon.service;

import com.hotric.megafon.models.Role;

import java.util.List;

public interface RoleService {
    
    void addRole(Role role);
    
    List<Role> getAllRoles();
}
