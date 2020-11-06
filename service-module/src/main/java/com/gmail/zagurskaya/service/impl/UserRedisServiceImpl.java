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
    private static final Long TIME_LIVE = 86400L;

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
        userRedis.setTimeToLive(TIME_LIVE);
        userRedisRepository.save(userRedis);
    }

    @Override
    public UserRedisDTO getUserById(String id) {
        UserRedis user = userRedisRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User from Redis not found with id " + id));
        return userRedisConverter.toDTO(user);
    }
}
