package com.example.library.service;

import com.example.library.entity.AppUser;
import org.apache.catalina.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    AppUser createUser(AppUser user);

//    User createUser(User user);
//    List<AppUser> getAllUsers();
//    Optional<AppUser> getUserById(Long id);
//    User updateUser(Long id, User userDetails);

    User createUser(User user);

    List<AppUser> getAllUsers();

    Optional<AppUser> getUserById(Long id);

    User updateUser(Long id, User userDetails);

    AppUser updateUser(Long id, AppUser userDetails);

    void deleteUser(Long id);
}

