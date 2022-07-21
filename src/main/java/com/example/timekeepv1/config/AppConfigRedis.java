package com.example.timekeepv1.config;

import com.example.timekeepv1.auth.StaffOutPutLoginDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class AppConfigRedis {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private Integer port;
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory lcf = new LettuceConnectionFactory();
        lcf.setHostName(host);
        lcf.setPort(port);
        lcf.afterPropertiesSet();
        return lcf;
        //return new LettuceConnectionFactory();
    }
    @Bean
    public RedisTemplate<String, StaffOutPutLoginDto> redisTemplate(){
        RedisTemplate<String, StaffOutPutLoginDto> empTemplate = new RedisTemplate<>();

        empTemplate.setConnectionFactory(redisConnectionFactory());
        return empTemplate;
    }
    @Bean
    public RedisTemplate<String, StaffOutPutLoginDto> redisStaffOutPutLogin(){
        RedisTemplate<String, StaffOutPutLoginDto> empTemplate = new RedisTemplate<>();
        empTemplate.setConnectionFactory(redisConnectionFactory());
        return empTemplate;
    }
}
