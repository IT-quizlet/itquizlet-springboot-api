package com.example.itquizletspringbootapi.service.impl;

import com.example.itquizletspringbootapi.dto.userresponse.UserResponseCreateDto;
import com.example.itquizletspringbootapi.dto.userresponse.UserResponseDto;
import com.example.itquizletspringbootapi.repository.UserResponseRepository;
import com.example.itquizletspringbootapi.service.UserResponseService;
import com.example.itquizletspringbootapi.service.mapper.UserResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserResponseServiceImpl implements UserResponseService {

    private final UserResponseRepository userResponseRepository;
    private final UserResponseMapper userResponseMapper;

    @Override
    public UserResponseDto saveUserResponse(UserResponseCreateDto userResponse) {
        return null;
    }

    @Override
    public List<UserResponseDto> getResponsesByUsername(String username) {
        return List.of();
    }

    @Override
    public List<UserResponseDto> getResponsesByQuiz(UUID quizId) {
        return List.of();
    }

    @Override
    public Boolean checkIfAnswerIsCorrect(UUID answerId) {
        return null;
    }
}
