package com.example.kameleoon.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewUserRequestDTO {
    private String name;
    private String email;
    private String password;

}
