package com.redis.cache;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * Redis工具类
 * 
 * 操作Redis服务器的增，删，改，查
 * @author Mark
 *
 */
public final class RedisUtils {
	private Logger logger = Logger.getLogger(RedisUtils.class);
	
	private RedisTemplate<Serializable, Object> redisTemplate;
	
	//设置redisTemplate
	public RedisTemplate<Serializable, Object> getRedisTemplate() {
		return redisTemplate;
	}



	public void setRedisTemplate(RedisTemplate<Serializable, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}



	/**
	 * 批量删除key对应的value
	 * @param key	//类型：String... 表示不定个数的String 类型参数
	 */
	public void removeValues(String... keys){
		for(String key:keys){
			remove(key);
		}
	}

	
	
	/**
	 * 删除指定keyd的value值
	 * @param key
	 */
	public void remove(String key) {
		if(exists(key)){
			redisTemplate.delete(key);//此处是删除指定值
		}
		
	}

	/**
	 * 批量删除key值
	 */
	public void removeKey(String pattern){
		Set<Serializable> keys=redisTemplate.keys(pattern);
		if(keys.size() > 0){
			redisTemplate.delete(keys);//此处是删除集合
		}
	}
	
	
	/**
	 * 判断redis服务器中是否存在key
	 * @param key
	 * @return
	 */
	public boolean exists(String key) {
		return redisTemplate.hasKey(key);
	}
	
	
	/**
	 * 读取缓存
	 * @param key 根据key查询缓存
	 */
	public Object get(String key){
		ValueOperations<Serializable, Object> operations =redisTemplate.opsForValue();
		return operations.get(key);
	}
	
	/**
	 * 写入缓存
	 * @param key
	 * @param value
	 */
	public boolean setCache( String key ,Object value){
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations =redisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 写入缓存，并设置时间
	 */
	
	public boolean setCacheTime(String key ,Object value,Long exprieTime){
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations =redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, exprieTime, TimeUnit.SECONDS);//设置key的过期时间，时间单位为S
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	
	

}
