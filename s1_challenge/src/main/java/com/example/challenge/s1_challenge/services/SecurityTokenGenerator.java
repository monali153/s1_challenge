package com.example.challenge.s1_challenge.services;

import com.example.challenge.s1_challenge.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {

    Map<String, String> generateToken(User user);
}
