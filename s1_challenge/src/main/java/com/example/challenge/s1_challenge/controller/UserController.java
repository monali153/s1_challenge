package com.example.challenge.s1_challenge.controller;

import com.example.challenge.s1_challenge.domain.User;
import com.example.challenge.s1_challenge.exception.UserNotFoundException;
import com.example.challenge.s1_challenge.services.SecurityTokenGenerator;
import com.example.challenge.s1_challenge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
public class UserController {

    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserService userService,SecurityTokenGenerator securityTokenGenerator){
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException {
        Map<String , String> map = null;
        try {
            User user1 = userService.findByUsernameAndPassword(user.getUsername(),user.getPassword());
            if(user1.getUsername().equals(user.getUsername())){
                map = securityTokenGenerator.generateToken(user);
            }
            return new ResponseEntity(map, HttpStatus.OK);

        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }catch (Exception ex){
            return new ResponseEntity<>("Try after some time",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> insertUser(@RequestBody User user){
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/userdata/v1/fetchusers")
    public ResponseEntity<?> getUser() {

        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @DeleteMapping("/userdata/v1/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable int userId) throws UserNotFoundException {
        ResponseEntity responseEntity;

        try {
            userService.deleteUser(userId);
            responseEntity = new ResponseEntity<>("Successfully deleted user",HttpStatus.OK);
        }catch (UserNotFoundException ex){
            throw new UserNotFoundException();
        }catch (Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }



}
