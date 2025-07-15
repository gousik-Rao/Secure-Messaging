package com.military.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.military.DTO.AuthResponseDTO;
import com.military.DTO.UserDTO;
import com.military.Entity.User;
import com.military.Exceptions.InvalidCredentialsException;
import com.military.Exceptions.UserExistsException;
import com.military.Exceptions.UserNotFoundException;
import com.military.Repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

//	New user registration
	public User registerUser(User user) {
		// This line is mocked in the UserTestService file.
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		user.setAuthProvider(User.AuthProvider.LOCAL);
		
		String email = user.getEmail();
		if(userRepo.findByEmail(email).isPresent())
			throw new UserExistsException("User with email " + email + " already exists");
		
		return userRepo.save(user);
	}
	
//	Validate user login
	public AuthResponseDTO validatingUser(String email, String password) {
		 User user = userRepo.findByEmail(email)
				 						  .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found!"));	
		 
//         Validate password with the hashed one using BCrypt
         if(!passwordEncoder.matches(password, user.getPassword())) {
        	 System.out.println(user.getPassword());
        	 throw new InvalidCredentialsException("Invalid email or Password");
        	 
         }
         return 
        		 new AuthResponseDTO(
    			 user.getUserId(),
    			 user.getUsername(),
    			 user.getEmail(),
    			 user.getRole().name(),
    			 user.getTeamCode(),
    			 "Login Successful"
    	);
    }
	
//	Updating user details
	public boolean updateUser(Long id, UserDTO user) {
		Optional<User> existingUser = userRepo.findById(id);
		if(existingUser.isPresent()) {
			User updatedUser = existingUser.get();
			updatedUser.setEmail(user.email());
			updatedUser.setUsername(user.username());
			updatedUser.setRole(user.role());
			updatedUser.setTeamCode(user.teamCode());
			
			userRepo.save(updatedUser);
			return true;
		}
		else
			throw new UserNotFoundException("User not found");
	}

//	Get user by their email
	public Optional<User> getUserByEmail(String email) {return userRepo.findByEmail(email);}           
	
//	Get user by their id
	public User getUserById(long id) { return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found")); }
	
//	Delete users by their user id
	public boolean deleteUserById(long userId) {
		if(userRepo.existsById(userId)) { // This existsUserById() will check whether the userId exists or not
			userRepo.deleteById(userId);
			return true;
		}
		return false;
	}
	
//	To reset the password
	public boolean resetPassword(String email, String password) {
		Optional<User> optionalUser = getUserByEmail(email);
		User existingUser = optionalUser.get();
		
		String hashedPassword = passwordEncoder.encode(password); // Hash the plain text password
		existingUser.setPassword(hashedPassword); // update password in the user object
		
		userRepo.save(existingUser);
		return true;
	}                  
	
}


















