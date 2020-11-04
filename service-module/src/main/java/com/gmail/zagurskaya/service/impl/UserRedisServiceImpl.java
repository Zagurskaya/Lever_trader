package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.redis.UserRedisRepository;
import com.gmail.zagurskaya.redis.model.UserRedis;
import com.gmail.zagurskaya.service.UserRedisService;
import com.gmail.zagurskaya.service.converter.UserRedisConverter;
import com.gmail.zagurskaya.service.model.UserRedisDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserRedisServiceImpl implements UserRedisService {

    private final UserRedisConverter userRedisConverter;
    private final UserRedisRepository userRedisRepository;

    @Autowired
    public UserRedisServiceImpl(UserRedisConverter userRedisConverter, UserRedisRepository userRedisRepository) {
        this.userRedisConverter = userRedisConverter;
        this.userRedisRepository = userRedisRepository;
    }


    @Override
    public void add(UserRedisDTO userRedisDTO) {
        UserRedis userRedis = userRedisConverter.toEntity(userRedisDTO);
        userRedisRepository.save(userRedis);
    }

    @Override
    public UserRedisDTO getUserById(String id) {
        UserRedis loaded = userRedisRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("UserRedis not found with id" + id));
        return userRedisConverter.toDTO(loaded);
    }
}
