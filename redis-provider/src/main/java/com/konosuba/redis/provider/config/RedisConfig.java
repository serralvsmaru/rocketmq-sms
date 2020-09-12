package com.konosuba.redis.provider.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis 配置类
 *
 * @author konosuba
 */

@Configuration
public class RedisConfig {
    /**
     * 将 RedisTemplate 对象注册到容器中
     *
     * @param factory RedisConnectionFactory 工厂
     * @return RedisTemplate 对象
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 对 key 进行序列化
        // StringRedisSerializer 当需要存储 String 类型的 key 时序列化使用
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // 对 String 的 key 进行序列化
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // 对 Hash 的 key 进行序列化
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // 使用 FastJsonRedisSerializer 来序列化
        // 对值进行序列化
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<Object>(Object.class);
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        // 注入到 factory 工厂中
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

    /**
     * 将操作 string redis 类型的 ValueOperations 对象注册到容器
     *
     * @param redisTemplate RedisTemplate 对象
     * @return ValueOperations 对象
     */
    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    /**
     * 将操作 list redis 类型的 ListOperations 对象注册到容器
     *
     * @param redisTemplate RedisTemplate 对象
     * @return ListOperations 对象
     */
    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    /**
     * 将操作 set redis 类型的 SetOperations 对象注册到容器
     *
     * @param redisTemplate RedisTemplate 对象
     * @return SetOperations 对象
     */
    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    /**
     * 将操作 zSet redis 类型的 ZSetOperations 对象注册到容器
     *
     * @param redisTemplate RedisTemplate 对象
     * @return ZSetOperations 对象
     */
    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }

    /**
     * 将操作 hash redis 类型的 ZSetOperations 对象注册到容器
     *
     * @param redisTemplate RedisTemplate 对象
     * @return HashOperations 对象
     */
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }
}
