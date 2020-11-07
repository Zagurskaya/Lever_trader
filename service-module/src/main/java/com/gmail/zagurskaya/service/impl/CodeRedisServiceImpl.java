package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.redis.CodeRedisRepository;
import com.gmail.zagurskaya.redis.model.CodeRedis;
import com.gmail.zagurskaya.service.CodeRedisService;
import com.gmail.zagurskaya.service.converter.CodeRedisConverter;
import com.gmail.zagurskaya.service.model.CodeRedisDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CodeRedisServiceImpl implements CodeRedisService {
    private static final Long TIME_LIVE = 86400L;

    private final CodeRedisConverter codeRedisConverter;
    private final CodeRedisRepository codeRedisRepository;

    @Autowired
    public CodeRedisServiceImpl(CodeRedisConverter codeRedisConverter, CodeRedisRepository codeRedisRepository) {
        this.codeRedisConverter = codeRedisConverter;
        this.codeRedisRepository = codeRedisRepository;
    }


    @Override
    public void add(CodeRedisDTO codeRedisDTO) {
        CodeRedis codeRedis = codeRedisConverter.toEntity(codeRedisDTO);
        codeRedis.setTimeToLive(TIME_LIVE);
        codeRedisRepository.save(codeRedis);
    }

    @Override
    public CodeRedisDTO getCodeById(String id) {
        CodeRedis code = codeRedisRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Code from Redis not found with id " + id));
        return codeRedisConverter.toDTO(code);
    }
}
