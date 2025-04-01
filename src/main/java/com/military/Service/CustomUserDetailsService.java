package com.military.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.military.Entity.User;
import com.military.Repository.UserRepo;

@Service // This class is a custom implementation for the UserDetailsService interface to access the loadUserByUsername method               
public class CustomUserDetailsService implements UserDetailsService{

	private final UserRepo userRepo;
	
	
	public CustomUserDetailsService(@Autowired UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user = userRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), 
				user.getPassword(),
				List.of(new SimpleGrantedAuthority(user.getAuthRole())));
	}
}
