package com.military.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.military.Filter.JWTAuthFilter;
import com.military.Repository.UserRepo;
import com.military.Service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JWTAuthFilter jwtAuthFilter;
//	private final UserService userService;
	
	public SecurityConfig(@Autowired JWTAuthFilter filter) {
		jwtAuthFilter = filter;
//		userService = service;
	}
	
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers("/api/auth/**", "/api/user/register", "/oauth2/**").permitAll()
                    .requestMatchers("/messages/**").authenticated() // ✅ Correct matcher
                    .requestMatchers("/", "/login", "/register", "/error").permitAll()
                    .requestMatchers("/css/**", "/js/**", "/images/**", "/static/**", "/webjars/**").permitAll()

                    .anyRequest().authenticated()
            )
            .formLogin(login -> login
                    .loginPage("/login")  // Custom login page
                    .defaultSuccessUrl("/home", true) // Redirect after login
                    .permitAll()
                )
                .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
                )
            .httpBasic(Customizer.withDefaults())  // ✅ Use Basic Auth for APIs instead of formLogin
            .oauth2Login(Customizer.withDefaults()) // Keep OAuth2 login if needed
            .sessionManagement(session -> session
                .sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS)) // ✅ Make it stateless
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setContentType("application/json");
                    response.setStatus(401);
                    response.getWriter().write("{\"error\": \"Unauthorized\"}");
                }) // ✅ Return JSON instead of redirecting to login page
            );
        return http.build();
	}
	
	@Bean
	protected AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	protected UserDetailsService userDetailService(UserRepo userRepo) {
		return new CustomUserDetailsService(userRepo);
	}
	
	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}




















