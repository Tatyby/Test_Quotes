package com.example.kameleoon.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewQuoteRequestDTO {
    private String name;
    private Long userId;
}
