package com.example.challenge.s1_challenge.services;

import com.example.challenge.s1_challenge.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtSecurityTokenGenerator implements SecurityTokenGenerator{
    @Override
    public Map<String, String> generateToken(User user) {

        String jwtToken = null;
        jwtToken = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"mykey").compact();
        Map<String , String> map = new HashMap<>();
        map.put("token",jwtToken);
        map.put("message","User successfully logged in");
        return map;
    }
}
