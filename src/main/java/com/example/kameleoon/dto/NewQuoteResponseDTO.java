package com.example.kameleoon.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class NewQuoteResponseDTO {
    private Long id;
    private String name;
    private LocalDateTime dateOfCreation;
}
