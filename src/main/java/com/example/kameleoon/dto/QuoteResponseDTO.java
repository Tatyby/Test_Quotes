package com.example.kameleoon.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class QuoteResponseDTO {
    private Long id;
    private String name;
    private Long userId;
    private LocalDateTime dateOfCreation;
    private LocalDateTime dateOfChange;
    private Long voice;
}
