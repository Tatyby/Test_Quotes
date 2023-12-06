package com.example.kameleoon.service;


import com.example.kameleoon.dto.NewUserRequestDTO;
import com.example.kameleoon.dto.NewUserResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    NewUserResponseDTO addNewUser(NewUserRequestDTO newUserRequestDTO);
}
