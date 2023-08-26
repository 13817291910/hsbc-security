package com.hsbc.security.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.hsbc.security.model.RoleDO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Bean
    public Cache<String, Object> userCache(){
        return Caffeine.newBuilder().
                initialCapacity(100).build();
    }

    @Bean
    public Cache<String, RoleDO> roleCache(){
        return Caffeine.newBuilder().
                initialCapacity(100).build();
    }

    @Bean
    public Cache<String, String> userAuthCache(){
        return Caffeine.newBuilder().
                initialCapacity(100).expireAfterWrite(2, TimeUnit.HOURS).build();
    }

    @Bean
    public Cache<String, Set<String>> usersRolesCache(){
        return Caffeine.newBuilder().initialCapacity(100).build();
    }


}
