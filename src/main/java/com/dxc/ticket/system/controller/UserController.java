package com.dxc.ticket.system.controller;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.ticket.system.dto.UserDto;
import com.dxc.ticket.system.service.UserService;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        ResponseEntity response = null;
        try {
            String hashPwd = passwordEncoder.encode(userDto.getPassword());
            userDto.setPassword(hashPwd);
            UserDto updatedUser =  userService.createUser(userDto);
            if (updatedUser.getId() > 0) {
                return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
            }
        } catch (Exception ex) {
            if(ex instanceof DataIntegrityViolationException){
                Map<String,String> errors = new LinkedHashMap<>();
                errors.put("error","User Already exist! Choose different email");
                response = ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(errors);

            }else {
                response = ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Internal Server error");
            }
        }
        return response;

    }

    @GetMapping("/signin")
    public ResponseEntity<UserDto> signIn(Authentication userDto) {
        UserDto user = userService.getUserByEmail(userDto.getName());
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    
    

}
