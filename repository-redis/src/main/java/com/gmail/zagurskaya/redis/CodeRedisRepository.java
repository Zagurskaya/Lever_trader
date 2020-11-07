package com.gmail.zagurskaya.redis;


import com.gmail.zagurskaya.redis.model.CodeRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CodeRedisRepository extends CrudRepository<CodeRedis, String> {

}
