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
@RedisHash("Code")
public class CodeRedis implements Serializable {

    private String id;

    private String value;

    @TimeToLive()
    private Long timeToLive;

}
