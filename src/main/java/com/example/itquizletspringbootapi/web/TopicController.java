package com.example.itquizletspringbootapi.web;

import com.example.itquizletspringbootapi.dto.topic.TopicDTO;
import com.example.itquizletspringbootapi.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics")
public class TopicController {
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    public void addTopic(@RequestBody TopicDTO topicDTO) {
        topicService.addTopic(topicDTO);
    }
}
