package com.gmail.zagurskaya.service.converter.impl;

import com.gmail.zagurskaya.redis.model.CodeRedis;
import com.gmail.zagurskaya.service.converter.CodeRedisConverter;
import com.gmail.zagurskaya.service.model.CodeRedisDTO;
import org.springframework.stereotype.Component;

@Component
public class CodeRedisConverterImpl implements CodeRedisConverter {

    @Override
    public CodeRedisDTO toDTO(CodeRedis codeRedis) {
        CodeRedisDTO codeRedisDTO = new CodeRedisDTO();
        codeRedisDTO.setId(codeRedis.getId());
        codeRedisDTO.setMail(codeRedis.getValue());
        return codeRedisDTO;
    }

    @Override
    public CodeRedis toEntity(CodeRedisDTO codeRedisDTO) {
        CodeRedis codeRedis = new CodeRedis();
        codeRedis.setId(codeRedisDTO.getId());
        codeRedis.setValue(codeRedisDTO.getMail());
        return codeRedis;
    }
}
