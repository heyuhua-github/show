package com.show.comm.conf.redis;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis资源
 * 
 * <pre>
 * byte[]数据：
 * RedisTemplate<byte[], byte[]>
 * </pre>
 * 
 * @author HeHongxin
 * @date 2017-3-29
 * 
 */
@Configuration
public class RedisConfig {

	@Value("${redis.host}")
	private String host;
	@Value("${redis.port}")
	private int port;
	@Value("${redis.password}")
	private String password;
	@Value("${redis.database}")
	private int database;
	@Value("${redis.pool.maxTotal}")
	private int maxTotal = GenericObjectPoolConfig.DEFAULT_MAX_TOTAL;
	@Value("${redis.pool.maxIdle}")
	private int maxIdle = GenericObjectPoolConfig.DEFAULT_MAX_IDLE;
	@Value("${redis.pool.maxWaitMillis}")
	private long maxWaitMillis = GenericObjectPoolConfig.DEFAULT_MAX_WAIT_MILLIS;

	/** Jedis connection factiry */
	private JedisConnectionFactory factory = null;
	/** Redis template */
	private RedisTemplate<byte[], byte[]> redis;

	/** 构造Jedis连接工厂 */
	synchronized private JedisConnectionFactory _jedisConnectionFactory() {
		if (null == factory) {
			JedisPoolConfig poolConfig = new JedisPoolConfig();
			poolConfig.setMaxTotal(maxTotal);
			poolConfig.setMaxIdle(maxIdle);
			poolConfig.setMaxWaitMillis(maxWaitMillis);

			factory = new JedisConnectionFactory();
			factory.setHostName(host);
			factory.setPort(port);
			factory.setPassword(password);
			factory.setDatabase(database);
			factory.setPoolConfig(poolConfig);
			factory.afterPropertiesSet();
		}
		return factory;
	}

	/** 构造Redis template */
	synchronized private RedisTemplate<byte[], byte[]> _redisTemplate() {
		if (null == redis) {
			redis = new RedisTemplate<>();
			redis.setEnableDefaultSerializer(false);
			redis.setConnectionFactory(_jedisConnectionFactory());
			redis.afterPropertiesSet();
		}
		return redis;
	}

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		return _jedisConnectionFactory();
	}

	@Bean(name = "redis")
	public RedisTemplate<byte[], byte[]> redisTemplate() {
		return _redisTemplate();
	}

	/** Reids - template - hash 操作 */
	@Bean(name = "reids-hash")
	public HashOperations<byte[], byte[], byte[]> redisHashOperations() {
		return _redisTemplate().opsForHash();
	}

	/** Reids - template - set 操作 */
	@Bean(name = "reids-set")
	public SetOperations<byte[], byte[]> redisSetOperations() {
		return _redisTemplate().opsForSet();
	}

	/** Reids - template - zset 操作 */
	@Bean(name = "reids-zset")
	public ZSetOperations<byte[], byte[]> redisZSetOperations() {
		return _redisTemplate().opsForZSet();
	}

	/** Reids - template - list 操作 */
	@Bean(name = "reids-list")
	public ListOperations<byte[], byte[]> redisListOperations() {
		return _redisTemplate().opsForList();
	}

	/** Reids - template - value 操作 */
	@Bean(name = "reids-value")
	public ValueOperations<byte[], byte[]> reidsValueOperations() {
		return _redisTemplate().opsForValue();
	}

}
