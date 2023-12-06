package com.example.kameleoon.service;

import com.example.kameleoon.dto.NewQuoteRequestDTO;
import com.example.kameleoon.dto.NewQuoteResponseDTO;
import com.example.kameleoon.dto.QuoteResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuoteService {
    NewQuoteResponseDTO addNewQuote(NewQuoteRequestDTO newQuote);

    List<QuoteResponseDTO> getAllQuote();

    QuoteResponseDTO getRandomQuote();

    void deleteQuote(Long quoteId);

    QuoteResponseDTO votingIncrementForQuote(Long quoteId);

    QuoteResponseDTO votingDecrementForQuote(Long quoteId);

    List<QuoteResponseDTO> get10BestQuote();

    List<QuoteResponseDTO> get10WorstQuote();
}
