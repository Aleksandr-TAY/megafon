package com.hotric.megafon.service;

import com.hotric.megafon.models.Role;
import com.hotric.megafon.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }
    
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
