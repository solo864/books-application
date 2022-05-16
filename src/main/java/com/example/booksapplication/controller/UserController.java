package com.example.booksapplication.controller;

import com.example.booksapplication.model.dto.UserRegisterDto;
import com.example.booksapplication.service.AuthService;
import com.example.booksapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping()
    public void registerAsUser(@RequestBody @Valid UserRegisterDto user) {
        userService.registerUser(user);
    }

}


