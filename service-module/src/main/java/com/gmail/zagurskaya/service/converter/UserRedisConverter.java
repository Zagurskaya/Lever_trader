package com.gmail.zagurskaya.service.converter;

import com.gmail.zagurskaya.redis.model.UserRedis;
import com.gmail.zagurskaya.service.model.UserRedisDTO;

public interface UserRedisConverter {

    UserRedisDTO toDTO(UserRedis userRedis);

    UserRedis toEntity(UserRedisDTO userRedisDTO);

}
