package com.example.itquizletspringbootapi.web;

import com.example.itquizletspringbootapi.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

}
