package com.longmaple.ttmall.productsvr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.longmaple.ttmall.productsvr.config.ServiceConfig;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ProductServiceApplication {

	@Autowired
	private ServiceConfig serviceConfig;

	private JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration =
				new RedisStandaloneConfiguration(serviceConfig.getRedisServer(),
						serviceConfig.getRedisPort());
	    return new JedisConnectionFactory(redisStandaloneConfiguration);
	}
	
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}
}
