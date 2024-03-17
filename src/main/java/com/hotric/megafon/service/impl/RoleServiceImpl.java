package com.hotric.megafon.service.impl;

import com.hotric.megafon.models.Role;
import com.hotric.megafon.models.repository.RoleRepository;
import com.hotric.megafon.service.inter.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

}
