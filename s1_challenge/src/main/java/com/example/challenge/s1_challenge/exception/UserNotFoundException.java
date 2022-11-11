package com.example.challenge.s1_challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "User has not found")
public class UserNotFoundException extends Exception{
}
