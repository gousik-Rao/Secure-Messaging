package com.military.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.military.dto.UserDTO;
import com.military.service.UserService;

@RestController("/api/user")
public class UserProfileUpdateController {

    private UserService userService;

//    @Override
//    public void init() throws ServletException {
//        super.init();
//        setUserService(ServiceLocator.getUserService());
//        userService.setUserRepo(ServiceLocator.getUserRepo());
//    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateprofile(@PathVariable long id, @RequestBody UserDTO userDto){
    	return userService.updateUser(id, userDto) ? 
    			ResponseEntity.ok("User updated successfully") :
    			ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
    }

}
