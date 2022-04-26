package com.example.timekeepv1.config;

import com.example.timekeepv1.auth.StaffOutPutLoginDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class AppConfigRedis {
    //Creating Connection with Redis
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory lcf = new LettuceConnectionFactory();
        lcf.setHostName("127.0.0.1");
        lcf.setPort(6379);
        lcf.afterPropertiesSet();
        return lcf;
        //return new LettuceConnectionFactory();
    }

    //Creating RedisTemplate for Entity 'Employee'
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
