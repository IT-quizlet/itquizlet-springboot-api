package com.example.itquizletspringbootapi.service.impl;

import com.example.itquizletspringbootapi.dto.userresponse.UserResponseCreateDto;
import com.example.itquizletspringbootapi.dto.userresponse.UserResponseDto;
import com.example.itquizletspringbootapi.repository.UserResponseRepository;
import com.example.itquizletspringbootapi.repository.entity.UserResponseEntity;
import com.example.itquizletspringbootapi.service.UserResponseService;
import com.example.itquizletspringbootapi.service.mapper.UserResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserResponseServiceImpl implements UserResponseService {

    private final UserResponseRepository userResponseRepository;
    private final UserResponseMapper userResponseMapper;

    @Override
    @Transactional
    public UserResponseDto saveUserResponse(UserResponseCreateDto userResponseCreateDto) {
        log.info("Saving user response: {}", userResponseCreateDto);

        UserResponseEntity userResponseEntity = userResponseMapper.toEntity(userResponseCreateDto);
        UserResponseEntity savedUserResponseEntity = userResponseRepository.save(userResponseEntity);

        log.info("User response saved with ID: {}", savedUserResponseEntity.getId());
        return userResponseMapper.toDto(savedUserResponseEntity);
    }

    @Override
    public List<UserResponseDto> getResponsesByUsername(String username) {
        List<UserResponseEntity> userResponses = userResponseRepository.findUserResponsesByUser_Username(username);
        log.info("Retrieved {} user responses from user: {}", userResponses.size(), username);
        return userResponses.stream()
                .map(userResponseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDto> getResponsesByQuiz(UUID quizId) {
        List<UserResponseEntity> userResponses = userResponseRepository.findByQuizId(quizId);
        log.info("Retrieved {} user responses from quiz with ID: {}", userResponses.size(), quizId);
        return userResponses.stream()
                .map(userResponseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean checkIfAnswerIsCorrect(UUID answerId) {
        log.info("Checking if answer with ID {} is correct", answerId);
        return true;
    }
}
