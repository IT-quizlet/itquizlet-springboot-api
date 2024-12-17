package com.example.itquizletspringbootapi.service;

import com.example.itquizletspringbootapi.dto.userresponse.UserResponseCreateDto;
import com.example.itquizletspringbootapi.dto.userresponse.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserResponseService {

    UserResponseDto saveUserResponse(UserResponseCreateDto userResponse);
    List<UserResponseDto> getResponsesByUsername(String username);
    List<UserResponseDto> getResponsesByQuiz(UUID quizId);
    Boolean checkIfAnswerIsCorrect(UUID answerId);

}
