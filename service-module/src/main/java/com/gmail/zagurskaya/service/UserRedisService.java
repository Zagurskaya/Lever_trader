package com.gmail.zagurskaya.service;

import com.gmail.zagurskaya.service.model.UserRedisDTO;

public interface UserRedisService {

    void add(UserRedisDTO user);

    UserRedisDTO getUserById(String id);

}
