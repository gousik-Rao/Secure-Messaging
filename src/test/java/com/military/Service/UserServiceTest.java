package com.military.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;  // ✅ Correct import
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.military.entity.User;
import com.military.exceptions.UserExistsException;
import com.military.repository.UserRepo;
import com.military.service.UserService;

public class UserServiceTest {

	@Mock
	private UserRepo userRepo;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@InjectMocks
	private UserService userService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
    // ✅ Test Case 1: Register a New User Successfully
	@Test
	void testRegisterUser_WhenUserDoesNotExists() {
		User newUser = new User();
		newUser.setEmail("test@gmail.com");
		newUser.setPassword("password123");
		newUser.setUsername("Test User");
		
		when(userRepo.findByEmail("test@gmail.com")).thenReturn(Optional.empty());
		
		when(passwordEncoder.encode("password123")).thenReturn("encoded-password123");
		
		when(userRepo.save(any(User.class))).thenReturn(newUser);
		
		User savedUser = userService.registerUser(newUser);
		
		assertNotNull(savedUser);
		assertEquals("test@gmail.com", savedUser.getEmail());
	}
	
    // ✅ Test Case 2: Register a User That Already Exists
	@Test
	void testRegisterUser_WhenUserAlreadyExists() {
		User existingUser = new User();
		
		existingUser.setEmail("existing@gmail.com");
		
		when(userRepo.findByEmail("existing@gmail.com")).thenReturn(Optional.of(existingUser));           
		
		assertThrows(UserExistsException.class, () -> {
			userService.registerUser(existingUser);
		});
		
		verify(userRepo, never()).save(any(User.class));
	}
	
    // ✅ Test Case 3: Fetch a User by Email
	@Test
    void testGetUserByEmail() {
        // Arrange
        User user = new User();
        user.setEmail("test@gmail.com");

        when(userRepo.findByEmail("test@gmail.com")).thenReturn(Optional.of(user)); // Simulate user found

        // Act
        Optional<User> foundUser = userService.getUserByEmail("test@gmail.com");

        // Assert
        assertTrue(foundUser.isPresent());
        assertEquals("test@gmail.com", foundUser.get().getEmail());
    }

    // ✅ Test Case 4: Fetch a Non-Existing User
    @Test
    void testGetUserByEmail_WhenUserNotFound() {
        // Arrange
        when(userRepo.findByEmail("unknown@gmail.com")).thenReturn(Optional.empty()); // Simulate user not found                              

        // Act
        Optional<User> foundUser = userService.getUserByEmail("unknown@gmail.com");

        // Assert
        assertFalse(foundUser.isPresent());
    }
}



















