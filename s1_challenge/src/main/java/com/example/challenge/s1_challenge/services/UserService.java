package com.example.challenge.s1_challenge.services;

import com.example.challenge.s1_challenge.domain.User;
import com.example.challenge.s1_challenge.exception.UserNotFoundException;


import java.util.List;

public interface UserService {

    public User addUser(User user);
    public User findByUsernameAndPassword(String username, String password) throws UserNotFoundException;
    public List<User> getAllUsers();

    public boolean deleteUser(int userId) throws UserNotFoundException;
}
