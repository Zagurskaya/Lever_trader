package com.gmail.zagurskaya.service.converter.impl;

import com.gmail.zagurskaya.redis.model.UserRedis;
import com.gmail.zagurskaya.service.converter.UserRedisConverter;
import com.gmail.zagurskaya.service.model.UserRedisDTO;
import org.springframework.stereotype.Component;

@Component
public class UserRedisConverterImpl implements UserRedisConverter {

    @Override
    public UserRedisDTO toDTO(UserRedis userRedis) {
        UserRedisDTO userRedisDTO = new UserRedisDTO();
        userRedisDTO.setId(userRedis.getId());
        userRedisDTO.setUsername(userRedis.getUsername());
        userRedisDTO.setFirstName(userRedis.getFirstName());
        userRedisDTO.setLastName(userRedis.getLastName());
        userRedisDTO.setRole(userRedis.getRole());
        userRedisDTO.setEmail(userRedis.getEmail());
        userRedisDTO.setCreatedData(userRedis.getCreatedData().atTime(00, 00, 00));
        return userRedisDTO;
    }

    @Override
    public UserRedis toEntity(UserRedisDTO userRedisDTO) {
        UserRedis userRedis = new UserRedis();
        userRedis.setId(userRedisDTO.getId());
        userRedis.setUsername(userRedisDTO.getUsername());
        userRedis.setFirstName(userRedisDTO.getFirstName());
        userRedis.setLastName(userRedisDTO.getLastName());
        userRedis.setRole(userRedisDTO.getRole());
        userRedis.setEmail(userRedisDTO.getEmail());
        userRedis.setCreatedData(userRedisDTO.getCreatedData().toLocalDate());
        return userRedis;
    }
}
