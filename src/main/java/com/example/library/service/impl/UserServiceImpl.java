package com.example.library.service.impl;

import com.example.library.entity.AppUser;
import com.example.library.exception.UserNotFoundException;
import com.example.library.repository.UserRepository;
import com.example.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public AppUser createUser(AppUser user) {
        return userRepository.save(user);
    }

    /**
     * @param user
     * @return
     */
    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<AppUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * @param id
     * @param userDetails
     * @return
     */
    @Override
    public User updateUser(Long id, User userDetails) {
        return null;
    }

    @Override
    public AppUser updateUser(Long id, AppUser userDetails) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setUsername(userDetails.getUsername());
                    existingUser.setPassword(userDetails.getPassword());
                    existingUser.setEmail(userDetails.getEmail());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}