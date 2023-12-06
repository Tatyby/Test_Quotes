package com.example.kameleoon.controller;

import com.example.kameleoon.dto.NewUserRequestDTO;
import com.example.kameleoon.dto.NewUserResponseDTO;
import com.example.kameleoon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/addNew")
    public ResponseEntity<NewUserResponseDTO> addNewUser(@RequestBody NewUserRequestDTO newUser) {
        return new ResponseEntity<>(userService.addNewUser(newUser), HttpStatus.CREATED);
    }

}
