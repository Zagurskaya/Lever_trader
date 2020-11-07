package com.gmail.zagurskaya.service;

import com.gmail.zagurskaya.service.model.CodeRedisDTO;

public interface CodeRedisService {

    void add(CodeRedisDTO code);

    CodeRedisDTO getCodeById(String id);

}
