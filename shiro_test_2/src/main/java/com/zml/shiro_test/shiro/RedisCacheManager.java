package com.zml.shiro_test.shiro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.PrincipalCollection;

import com.zml.shiro_test.redis.RedisClient;

public class RedisCacheManager implements CacheManager {

	private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();
	private String prefix = "shiro_cache:";
	private RedisClient redisClient;
	@Override
	public <K,V> Cache<K,V> getCache(String key) throws CacheException {
		Cache cache = caches.get(key);
		if(null == cache){
			cache = new RedisCache(prefix, redisClient);
			caches.put(key, cache);
		}
		return cache;
	}
}

class RedisCache<K,V> implements Cache<K,V>{

	private String prefix = "";
	private RedisClient redisClient;
	RedisCache(String prefix,RedisClient redisClient){
		this.prefix = prefix;
		this.redisClient = redisClient;
	}
	@Override
	public void clear() throws CacheException {
		redisClient.clear();
	}

	@Override
	public V get(K key) throws CacheException {
		return redisClient.getObject(getQueryName(key));
	}

	private String getQueryName(K key) {
		if (key instanceof String) {
			String preKey = this.prefix + key;
			return preKey;
		} else if (key instanceof PrincipalCollection) {
			String preKey = this.prefix + key.toString();
			return preKey;
		} else {
			return this.prefix + key.toString();
		}
	}
	@Override
	public Set<K> keys() {
		Set<String> keySet = redisClient.keySet(prefix+"*");
		Set<K> keySet2 = new HashSet<K>();
		for (String str : keySet) {
			keySet2.add((K)str);
		}
		return keySet2;
	}

	@Override
	public V put(K key, V value) throws CacheException {
		redisClient.setObject(getQueryName(key), value);
		return value;
	}

	@Override
	public V remove(K key) throws CacheException {
		V v = get(key);
		redisClient.delete(getQueryName(key));
		return v;
	}

	@Override
	public int size() {
		return redisClient.size();
	}

	@Override
	public Collection<V> values() {
		Set<String> keySet = redisClient.keySet(prefix+"*");
		List<V> list = new ArrayList<>(keySet.size());
		for (String str : keySet) {
			V v = redisClient.getObject(str);
			list.add(v);
		}
		return list;
	}

	
}
