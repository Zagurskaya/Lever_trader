package com.gmail.zagurskaya.service.converter;

import com.gmail.zagurskaya.redis.model.CodeRedis;
import com.gmail.zagurskaya.service.model.CodeRedisDTO;

public interface CodeRedisConverter {

    CodeRedisDTO toDTO(CodeRedis codeRedis);

    CodeRedis toEntity(CodeRedisDTO codeRedisDTO);

}
