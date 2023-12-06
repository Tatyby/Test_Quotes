package com.example.kameleoon.service;

import com.example.kameleoon.dto.NewUserRequestDTO;
import com.example.kameleoon.dto.NewUserResponseDTO;
import com.example.kameleoon.entity.UserInfoEntity;
import com.example.kameleoon.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserInfoRepository userInfoRepository;

    /**
     * Метод создает нового пользователя
     *
     * @param newUserRequestDTO (String name;
     *                          private String email;
     *                          private String password;)
     * @return NewUserResponseDTO (Long id;
     * String name;
     * LocalDateTime dateOfCreation;)
     */
    @Override
    @Transactional
    public NewUserResponseDTO addNewUser(NewUserRequestDTO newUserRequestDTO) {
        UserInfoEntity userInfoEntity = userInfoRepository.save(UserInfoEntity.builder()
                .name(newUserRequestDTO.getName())
                .dateOfCreation(LocalDateTime.now())
                .email(newUserRequestDTO.getEmail())
                .password(newUserRequestDTO.getPassword())
                .build());
        return NewUserResponseDTO.builder()
                .id(userInfoEntity.getId())
                .name(userInfoEntity.getName())
                .dateOfCreation(userInfoEntity.getDateOfCreation())
                .build();
    }
}
