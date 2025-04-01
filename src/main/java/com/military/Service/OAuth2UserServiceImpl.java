package com.military.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.military.Entity.User;
import com.military.Repository.UserRepo;

public class OAuth2UserServiceImpl extends DefaultOAuth2UserService{

	@Autowired
	private UserRepo  userRepo;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) 
			throws OAuth2AuthenticationException{
		OAuth2User oAuth2User = super.loadUser(userRequest);
		String email = oAuth2User.getAttribute("email");
		
		User user = userRepo.findByEmail(email).orElseGet(() -> {
			User newUser  = new User();
			newUser.setEmail(email);
			newUser.setUsername(oAuth2User.getAttribute("name"));
			newUser.setAuthProvider(User.AuthProvider.GOOGLE);
			newUser.setRole(User.Role.General); // Default Role
			return userRepo.save(newUser);
		});
		
		return new DefaultOAuth2User(
			List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())),
			oAuth2User.getAttributes(), "email"
		);
	}
}
