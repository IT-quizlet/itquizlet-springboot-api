package com.example.itquizletspringbootapi.service.impl;

import com.example.itquizletspringbootapi.dto.topic.TopicDTO;
import com.example.itquizletspringbootapi.repository.TopicRepository;
import com.example.itquizletspringbootapi.repository.entity.TopicEntity;
import com.example.itquizletspringbootapi.service.TopicService;
import com.example.itquizletspringbootapi.service.mapper.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository, TopicMapper topicMapper) {
        this.topicRepository = topicRepository;
        this.topicMapper = topicMapper;
    }

    @Override
    public void addTopic(TopicDTO topicDTO) {
        TopicEntity topicEntity = topicMapper.toEntity(topicDTO);
        topicRepository.save(topicEntity);
    }
}
