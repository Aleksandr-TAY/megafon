package com.hotric.megafon.service;

import com.hotric.megafon.models.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    
    void removeUserForId(long id);
    
    List<User> getAllUsers();
    
    User getUserById(long id);
    
    void updateUser(long id, User user);
}
