package com.matrix.springpracticeapp.controller;

import com.matrix.springpracticeapp.dto.LoginReq;
import com.matrix.springpracticeapp.dto.LoginRes;
import com.matrix.springpracticeapp.dto.UserDto;

import com.matrix.springpracticeapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/registration")
    public void registration(@RequestBody UserDto userDto){
        userService.registration(userDto);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody LoginReq loginReq){
        return userService.authenticate(loginReq);
    }
}


