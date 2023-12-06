package com.example.kameleoon.service;

import com.example.kameleoon.dto.NewQuoteRequestDTO;
import com.example.kameleoon.dto.NewQuoteResponseDTO;
import com.example.kameleoon.dto.QuoteResponseDTO;
import com.example.kameleoon.entity.QuoteEntity;
import com.example.kameleoon.entity.UserInfoEntity;
import com.example.kameleoon.exception.KameleoonException;
import com.example.kameleoon.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuoteServiceImp implements QuoteService {
    private final QuoteRepository quoteRepository;

    /**
     * Метод создает новую цитату
     *
     * @param newQuote (String name;
     *                 Long userId;)
     * @return NewQuoteResponseDTO (Long id;
     * String name;
     * LocalDateTime dateOfCreation;)
     */
    @Override
    @Transactional
    public NewQuoteResponseDTO addNewQuote(NewQuoteRequestDTO newQuote) {
        QuoteEntity quoteEntity = quoteRepository.save(QuoteEntity.builder()
                .name(newQuote.getName())
                .dateOfCreation(LocalDateTime.now())
                .userInfo(UserInfoEntity.builder()
                        .id(newQuote.getUserId())
                        .build())
                .voice(0L)
                .build());
        return NewQuoteResponseDTO.builder()
                .id(quoteEntity.getId())
                .name(quoteEntity.getName())
                .dateOfCreation(quoteEntity.getDateOfCreation())
                .build();
    }

    /**
     * Метод позволяет получить все цитаты
     *
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<QuoteResponseDTO> getAllQuote() {
        List<QuoteEntity> listQuoteDTO = quoteRepository.findAll();
        return mappedListQuoteEntityToListQuoteResponseDTO(listQuoteDTO);
    }

    /**
     * Метод позволяет получить рандомную цитату
     *
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public QuoteResponseDTO getRandomQuote() {
        QuoteEntity quoteEntity = quoteRepository.getQuoteRandom().orElseThrow(() -> new KameleoonException("Цитат нет в БД"));
        return mappedEntityToQuoteResponseDTO(quoteEntity);
    }

    /**
     * Метод позволят удалить цитату из базы данных
     *
     * @param quoteId
     */
    @Override
    @Transactional
    public void deleteQuote(Long quoteId) {
        quoteRepository.findById(quoteId).orElseThrow(() -> new KameleoonException("Цитаты под Id " + quoteId + " не найдено в БД"));
        quoteRepository.deleteById(quoteId);
    }

    /**
     * Метод позволяет проголосовать за цитату upvote
     *
     * @param quoteId
     * @return
     */
    @Override
    @Transactional
    public QuoteResponseDTO votingIncrementForQuote(Long quoteId) {
        QuoteEntity quoteEntity = quoteRepository.findById(quoteId).orElseThrow(() -> new KameleoonException("Цитаты под Id " + quoteId + " не найдено в БД"));
        quoteEntity.setVoice(quoteEntity.getVoice() + 1);
        quoteEntity.setDateOfChange(LocalDateTime.now());
        quoteRepository.save(quoteEntity);
        return mappedEntityToQuoteResponseDTO(quoteEntity);
    }

    /**
     * Метод позволяет проголосовать за цитату downvote
     *
     * @param quoteId
     * @return
     */
    @Override
    @Transactional
    public QuoteResponseDTO votingDecrementForQuote(Long quoteId) {
        QuoteEntity quoteEntity = quoteRepository.findById(quoteId).orElseThrow(() -> new KameleoonException("Цитаты под Id " + quoteId + " не найдено в БД"));
        quoteEntity.setVoice(quoteEntity.getVoice() - 1);
        quoteEntity.setDateOfChange(LocalDateTime.now());
        quoteRepository.save(quoteEntity);
        return mappedEntityToQuoteResponseDTO(quoteEntity);
    }

    /**
     * Метод позволяет получить 10 лучших цитат
     *
     * @return
     */
    @Override
    @Transactional
    public List<QuoteResponseDTO> get10BestQuote() {
        List<QuoteEntity> listQuoteDTO = quoteRepository.findTop10ByOrderByVoiceDesc();
        return mappedListQuoteEntityToListQuoteResponseDTO(listQuoteDTO);
    }

    /**
     * Метод позволяет получить 10 худших цитат
     *
     * @return
     */
    @Override
    @Transactional
    public List<QuoteResponseDTO> get10WorstQuote() {
        List<QuoteEntity> listQuoteDTO = quoteRepository.findTop10ByOrderByVoiceAsc();
        return mappedListQuoteEntityToListQuoteResponseDTO(listQuoteDTO);
    }

    /**
     * Маппинг из List< QuoteEntity> в List< QuoteResponseDTO>
     *
     * @param list
     * @return
     */
    private List<QuoteResponseDTO> mappedListQuoteEntityToListQuoteResponseDTO(List<QuoteEntity> list) {
        return list.stream()
                .map(quoteEntity -> {
                    return QuoteResponseDTO.builder()
                            .id(quoteEntity.getId())
                            .name(quoteEntity.getName())
                            .userId(quoteEntity.getUserInfo().getId())
                            .dateOfCreation(quoteEntity.getDateOfCreation())
                            .dateOfChange(quoteEntity.getDateOfChange())
                            .voice(quoteEntity.getVoice())
                            .build();
                })
                .collect(Collectors.toList());
    }

    private QuoteResponseDTO mappedEntityToQuoteResponseDTO(QuoteEntity quoteEntity) {
        return QuoteResponseDTO.builder()
                .id(quoteEntity.getId())
                .userId(quoteEntity.getUserInfo().getId())
                .name(quoteEntity.getName())
                .dateOfChange(quoteEntity.getDateOfChange())
                .dateOfCreation(quoteEntity.getDateOfCreation())
                .voice(quoteEntity.getVoice())
                .build();
    }
}
