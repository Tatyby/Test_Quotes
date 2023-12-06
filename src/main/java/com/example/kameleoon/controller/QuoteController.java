package com.example.kameleoon.controller;


import com.example.kameleoon.dto.NewQuoteRequestDTO;
import com.example.kameleoon.dto.NewQuoteResponseDTO;
import com.example.kameleoon.dto.QuoteResponseDTO;
import com.example.kameleoon.service.QuoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quote")
@RequiredArgsConstructor
@Slf4j
public class QuoteController {
    private final QuoteService quoteService;

    @PostMapping("/addNew")
    public ResponseEntity<NewQuoteResponseDTO> addNewQuote(@RequestBody NewQuoteRequestDTO newQuote) {
        return new ResponseEntity<>(quoteService.addNewQuote(newQuote), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuoteResponseDTO>> getAllQuote() {
        return new ResponseEntity<>(quoteService.getAllQuote(), HttpStatus.OK);
    }

    @GetMapping("/random")
    public ResponseEntity<QuoteResponseDTO> getRandomQuote() {
        return new ResponseEntity<>(quoteService.getRandomQuote(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{quoteId}")
    public ResponseEntity<?> deleteQuote(@PathVariable Long quoteId) {
        quoteService.deleteQuote(quoteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/increment/{quoteId}")
    public ResponseEntity<QuoteResponseDTO> votingIncrementForQuote(@PathVariable Long quoteId) {
        return new ResponseEntity<>(quoteService.votingIncrementForQuote(quoteId), HttpStatus.CREATED);
    }

    @PostMapping("/decrement/{quoteId}")
    public ResponseEntity<QuoteResponseDTO> votingDecrementForQuote(@PathVariable Long quoteId) {
        return new ResponseEntity<>(quoteService.votingDecrementForQuote(quoteId), HttpStatus.CREATED);
    }

    @GetMapping("/10BestQuote")
    public ResponseEntity<List<QuoteResponseDTO>> get10BestQuote() {
        return new ResponseEntity<>(quoteService.get10BestQuote(), HttpStatus.OK);
    }

    @GetMapping("/10WorstQuote")
    public ResponseEntity<List<QuoteResponseDTO>> get10WorstQuote() {
        return new ResponseEntity<>(quoteService.get10WorstQuote(), HttpStatus.OK);
    }
}
