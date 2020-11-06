package com.gmail.zagurskaya.redis.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;

@Getter
@Setter
@ToString
@RedisHash("User")
public class UserRedis implements Serializable {

    private String id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String role;

    @TimeToLive()
    private Long timeToLive;

}
