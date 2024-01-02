package ru.javawebinar.topjava;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import static ru.javawebinar.topjava.Profiles.*;

@Configuration
public class NoOpCacheManager {

    @Bean
    public CacheManager cacheManage() {
        return new CaffeineCacheManager();
    }

    @Bean
    @Profile({DATAJPA, JDBC, JPA} )
    @Primary
    public CacheManager noOpCacheManager() {
        return new org.springframework.cache.support.NoOpCacheManager();
    }
}
