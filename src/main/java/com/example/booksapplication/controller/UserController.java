package com.example.booksapplication.controller;

import com.example.booksapplication.model.dto.UserRegisterDto;
import com.example.booksapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAsUser(@RequestBody @Valid UserRegisterDto user) {
        userService.registerUser(user);
    }

    @GetMapping
    public void hey() {
        userService.hey();
    }

}


