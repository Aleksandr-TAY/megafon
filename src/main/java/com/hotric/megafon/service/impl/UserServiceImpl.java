package com.hotric.megafon.service.impl;

import com.hotric.megafon.models.Role;
import com.hotric.megafon.models.UserEntity;
import com.hotric.megafon.models.repository.UserRepository;
import com.hotric.megafon.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collection;
import java.util.stream.Collectors;

import static com.hotric.megafon.common.Constants.ROLE_ADMIN;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public void addUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Username not found! " + username));
    }
   
    @Override
    public UserDetails loadUserByUsername(String username)  {
        UserEntity user = findByUsername(username);
        return new User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }
    
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
    
    public boolean validateUser(String name){
        return userRepository.findAll().stream().noneMatch(user -> user.getUsername().equals(name));
    }
    public boolean validateUserByRoles(Principal principal){
        return loadUserByUsername(principal.getName()).getAuthorities().stream().anyMatch(role -> role.getAuthority().contains(ROLE_ADMIN));
    }

}
