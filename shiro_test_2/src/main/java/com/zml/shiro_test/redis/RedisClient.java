package com.zml.shiro_test.redis;

import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import com.zml.shiro_test.util.SerializeUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClient {
	
	private static JedisPool pool;
	
	static{
		ResourceBundle bundle = ResourceBundle.getBundle("redis");
		if(bundle==null){
			throw new RuntimeException("reids.propertis为空");
		}
		System.out.println(bundle);
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
		config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
		config.setMaxWait(Integer.valueOf(bundle.getString("redis.pool.maxWait")));
		config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
		config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));
		pool = new JedisPool(config, bundle.getString("redis.ip"));
	}

	
	public static JedisPool getPool() {
		return pool;
	}

	/**
	 * 获取序列化的值
	 * @param key
	 * @return
	 */
	public <T> T getObject(String key){
		T result = null;
		Jedis jedis = pool.getResource();
		byte[] bs = jedis.get(key.getBytes());
		result = (T) SerializeUtil.unserialize(bs);
		return result;
	}
	/**
	 * 设置序列化的对象
	 * @param key
	 * @param value
	 */
	public <T> void setObject(String key,T value){
		Jedis jedis = pool.getResource();
		jedis.set(key.getBytes(), SerializeUtil.serialize(value));
		pool.returnBrokenResource(jedis);
	}
	
	public void setString(String key,String value){
		Jedis jedis = pool.getResource();
		jedis.set(key, value);
		pool.returnBrokenResource(jedis);
	}
	public String getString(String key){
		Jedis jedis = pool.getResource();
		String result = jedis.get(key);
		pool.returnBrokenResource(jedis);
		return result;
		
	}

	/**
	 * 设置一个超时时间的值
	 * @param key
	 * @param ttl
	 * @param session
	 */
	public <T> void set(String key, int ttl, T value) {
		// TODO Auto-generated method stub
		Jedis jedis = pool.getResource();
		jedis.setex(key.getBytes(),ttl, SerializeUtil.serialize(value));
		pool.returnBrokenResource(jedis);
	}
	
	public void delete(String key){
		Jedis jedis = pool.getResource();
		jedis.del(key);
		pool.returnBrokenResource(jedis);
	}

	public Set<String> keySet(String keys) {
		Set<String> keysSet = new HashSet();
		Jedis jedis = pool.getResource();
		keysSet = jedis.keys(keys);
		pool.returnBrokenResource(jedis);
		return keysSet;
	}
	
}
