package com.example.library.service.impl;

import com.example.library.entity.AppUser;
import com.example.library.exception.UserNotFoundException;
import com.example.library.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createUser_ShouldSaveAndReturnUser() {
        AppUser inputUser = new AppUser();
        inputUser.setUsername("testuser");
        inputUser.setPassword("password");
        inputUser.setEmail("test@example.com");

        AppUser savedUser = new AppUser();
        savedUser.setId(1L);
        savedUser.setUsername("testuser");
        savedUser.setPassword("password");
        savedUser.setEmail("test@example.com");

        when(userRepository.save(any(AppUser.class))).thenReturn(savedUser);

        AppUser result = userService.createUser(inputUser);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("testuser", result.getUsername());
        assertEquals("test@example.com", result.getEmail());

        verify(userRepository).save(inputUser);
    }

    @Test
    void getAllUsers_ShouldReturnListOfUsers() {
        AppUser user1 = new AppUser();
        user1.setId(1L);
        user1.setUsername("user1");
        user1.setEmail("user1@example.com");

        AppUser user2 = new AppUser();
        user2.setId(2L);
        user2.setUsername("user2");
        user2.setEmail("user2@example.com");

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        List<AppUser> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals("user2", result.get(1).getUsername());

        verify(userRepository).findAll();
    }

    @Test
    void getUserById_WhenUserExists_ShouldReturnUser() {
        Long userId = 1L;
        AppUser user = new AppUser();
        user.setId(userId);
        user.setUsername("testuser");
        user.setEmail("test@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Optional<AppUser> result = userService.getUserById(userId);

        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getId());
        assertEquals("testuser", result.get().getUsername());

        verify(userRepository).findById(userId);
    }

    @Test
    void getUserById_WhenUserNotExists_ShouldReturnEmpty() {
        Long nonExistentId = 999L;
        when(userRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        Optional<AppUser> result = userService.getUserById(nonExistentId);

        assertTrue(result.isEmpty());
        verify(userRepository).findById(nonExistentId);
    }

    @Test
    void updateUser_WhenUserExists_ShouldUpdateAndReturnUser() {
        Long userId = 1L;

        AppUser existingUser = new AppUser();
        existingUser.setId(userId);
        existingUser.setUsername("olduser");
        existingUser.setPassword("oldpass");
        existingUser.setEmail("old@example.com");

        AppUser updateDetails = new AppUser();
        updateDetails.setUsername("newuser");
        updateDetails.setPassword("newpass");
        updateDetails.setEmail("new@example.com");

        AppUser updatedUser = new AppUser();
        updatedUser.setId(userId);
        updatedUser.setUsername("newuser");
        updatedUser.setPassword("newpass");
        updatedUser.setEmail("new@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(updatedUser);

        AppUser result = userService.updateUser(userId, updateDetails);

        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("newuser", result.getUsername());
        assertEquals("new@example.com", result.getEmail());

        assertEquals("newuser", existingUser.getUsername());
        assertEquals("newpass", existingUser.getPassword());
        assertEquals("new@example.com", existingUser.getEmail());

        verify(userRepository).findById(userId);
        verify(userRepository).save(existingUser);
    }

    @Test
    void updateUser_WhenUserNotExists_ShouldThrowException() {
        Long nonExistentId = 999L;
        AppUser updateDetails = new AppUser();
        updateDetails.setUsername("newuser");

        when(userRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userService.updateUser(nonExistentId, updateDetails);
        });

        assertTrue(exception.getMessage().contains(String.valueOf(nonExistentId)));
        verify(userRepository).findById(nonExistentId);
        verify(userRepository, never()).save(any(AppUser.class));
    }

    @Test
    void deleteUser_WhenUserExists_ShouldDeleteUser() {
        Long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(true);
        doNothing().when(userRepository).deleteById(userId);

        userService.deleteUser(userId);

        verify(userRepository).existsById(userId);
        verify(userRepository).deleteById(userId);
    }

    @Test
    void deleteUser_WhenUserNotExists_ShouldThrowException() {

        Long nonExistentId = 999L;
        when(userRepository.existsById(nonExistentId)).thenReturn(false);

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userService.deleteUser(nonExistentId);
        });

        assertTrue(exception.getMessage().contains(nonExistentId.toString()));
        verify(userRepository).existsById(nonExistentId);
        verify(userRepository, never()).deleteById(anyLong());
    }
}