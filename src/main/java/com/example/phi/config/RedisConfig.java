package com.example.phi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.example.phi.controller.PaymentsController;
import com.example.phi.model.PaymentModel;

/*
 * get beans of redis
 * @author playjava 
 */
@Configuration
public class RedisConfig {
	private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

	@Bean
	public JedisConnectionFactory getConnectionFactory() {

		logger.info("inside RedisConfig.getConnectionFactory()");

		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		return jedisConnectionFactory;
	}

	@Bean
	public RedisTemplate<String, PaymentModel> redisTemplate() {
		logger.info("inside RedisConfig.redisTemplate()");
		RedisTemplate<String, PaymentModel> redisTemplate = new RedisTemplate<String, PaymentModel>();
		redisTemplate.setConnectionFactory(getConnectionFactory());
		return redisTemplate;
	}

}
