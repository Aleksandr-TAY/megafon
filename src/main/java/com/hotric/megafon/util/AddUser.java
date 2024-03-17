package com.hotric.megafon.util;

import com.hotric.megafon.models.Role;
import com.hotric.megafon.models.UserEntity;
import com.hotric.megafon.service.impl.RoleServiceImpl;
import com.hotric.megafon.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.hotric.megafon.common.Constants.ROLE_ADMIN;
import static com.hotric.megafon.common.Constants.ROLE_BACKEND;
import static com.hotric.megafon.common.Constants.ROLE_FRONTEND;

@Component
@RequiredArgsConstructor
public class AddUser implements CommandLineRunner {
    
    private final RoleServiceImpl roleServiceImp;
    private final UserServiceImpl userServiceImp;
    
    @Override
    public void run(String... args) {
        Role roleFrontend = new Role(1L, ROLE_FRONTEND);
        Role roleBackend = new Role(2L, ROLE_BACKEND);
        Role roleAdmin = new Role(3L, ROLE_ADMIN);
        
        roleServiceImp.addRole(roleFrontend);
        roleServiceImp.addRole(roleBackend);
        roleServiceImp.addRole(roleAdmin);
        
        Set<Role> user1Set = Stream.of(roleFrontend).collect(Collectors.toSet());
        Set<Role> user2Set = Stream.of(roleFrontend, roleBackend).collect(Collectors.toSet());
        Set<Role> user3Set = Stream.of(roleBackend).collect(Collectors.toSet());
        Set<Role> adminSet = Stream.of(roleAdmin, roleFrontend, roleBackend).collect(Collectors.toSet());
        
        UserEntity user1 = new UserEntity(1L, "user1", "1", user1Set);
        UserEntity user2 = new UserEntity(2L, "user2", "2", user2Set);
        UserEntity user3 = new UserEntity(3L, "user3", "3", user3Set);
        UserEntity admin = new UserEntity(4L, "admin", "admin", adminSet);
        
        userServiceImp.addUser(user1);
        userServiceImp.addUser(user2);
        userServiceImp.addUser(user3);
        userServiceImp.addUser(admin);
    }
}
