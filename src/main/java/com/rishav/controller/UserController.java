package com.rishav.controller;

import com.rishav.config.JwtConstant;
import com.rishav.model.User;
import com.rishav.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(
            @RequestHeader(JwtConstant.JWT_HEADER) String jwt
    ) throws Exception {
        User user = userService.findByProfileByJwt(jwt);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
