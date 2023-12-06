package com.example.kameleoon.repository;

import com.example.kameleoon.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {
    @Query(value = "SELECT * FROM quote ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<QuoteEntity> getQuoteRandom();

    List<QuoteEntity> findTop10ByOrderByVoiceDesc();

    List<QuoteEntity> findTop10ByOrderByVoiceAsc();
}
