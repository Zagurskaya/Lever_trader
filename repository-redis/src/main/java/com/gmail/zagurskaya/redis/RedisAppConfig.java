package com.gmail.zagurskaya.redis;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.gmail.zagurskaya.*"})
@EnableAutoConfiguration
public class RedisAppConfig {
}
