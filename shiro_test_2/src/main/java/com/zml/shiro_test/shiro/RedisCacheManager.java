package com.zml.shiro_test.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

public class RedisCacheManager implements CacheManager{

	@Override
	public <K,V> Cache<K,V> getCache(String paramString) throws CacheException {
		return null;
	}}
