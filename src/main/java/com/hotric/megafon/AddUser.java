package com.hotric.megafon;

import com.hotric.megafon.models.Role;
import com.hotric.megafon.models.User;
import com.hotric.megafon.service.RoleServiceImpl;
import com.hotric.megafon.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AddUser implements CommandLineRunner {
    
    private RoleServiceImpl roleServiceImp;
    private UserServiceImpl userServiceImp;
    
    @Autowired
    public AddUser(RoleServiceImpl roleServiceImp, UserServiceImpl userServiceImp) {
        this.roleServiceImp = roleServiceImp;
        this.userServiceImp = userServiceImp;
    }
    
    @Override
    public void run(String... args) {
        Role roleFrontend = new Role(1L, "ROLE_FRONTEND");
        Role roleBackend = new Role(2L, "ROLE_BACKEND");
        Role roleAdmin = new Role(3L, "ROLE_ADMIN");
        
        roleServiceImp.addRole(roleFrontend);
        roleServiceImp.addRole(roleBackend);
        roleServiceImp.addRole(roleAdmin);
        
        Set<Role> user1Set = Stream.of(roleFrontend).collect(Collectors.toSet());
        Set<Role> user2Set = Stream.of(roleFrontend, roleBackend).collect(Collectors.toSet());
        Set<Role> user3Set = Stream.of(roleBackend).collect(Collectors.toSet());
        Set<Role> adminSet = Stream.of(roleAdmin, roleFrontend, roleBackend).collect(Collectors.toSet());
        
        User user1 = new User(1L, "user1", "1", user1Set);
        User user2 = new User(2L, "user2", "2", user2Set);
        User user3 = new User(3L, "user3", "3", user3Set);
        User admin = new User(4L, "admin", "admin", adminSet);
        
        userServiceImp.addUser(user1);
        userServiceImp.addUser(user2);
        userServiceImp.addUser(user3);
        userServiceImp.addUser(admin);
    }
}
