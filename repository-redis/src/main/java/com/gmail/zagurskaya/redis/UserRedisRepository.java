package com.gmail.zagurskaya.redis;


import com.gmail.zagurskaya.redis.model.UserRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRedisRepository extends CrudRepository<UserRedis, String> {

//    Optional<UserRedis> findById(String id);
}
