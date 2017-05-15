package com.redis.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * Redis测试服务实现
 * 
 * 后期可将缓存注解用于service实现中
 * @author Mark
 *
 */
public class RedisCacheTestServiceImpl {

	/*
	 * @Cacheable注解可以用在方法或者类级别。
	 * 		当他应用于方法级别的时候，只缓存当前方法的返回值。当应用在类级别的时候，这个类的所有方法的返回值都将被缓存。
	 * 
	 * @CacheEvict 注释来标记要清空缓存的方法，当这个方法被调用后，即会清空缓存。
	 * 
	 * @CachePut 注释，这个注释可以确保方法被执行，同时方法的返回值也被记录到缓存中，实现缓存与数据库的同步更新。
	 */
	
	
	
	/**
	 * 此类的getAccountByName 方法上有一个注释 annotation，即 @Cacheable(value=”accountCache”)，
	 * 这个注释的意思是，当调用这个方法的时候，会从一个名叫 accountCache 的缓存中查询，
	 * 如果没有，则执行实际的方法（即查询数据库），并将执行的结果存入缓存中，否则返回缓存中的对象。
	 * 这里的缓存中的 key 就是参数userName，value 就是Account 对象。“accountCache”缓存是在 spring*.xml 中定义的名称。
	 * @param number
	 * @return
	 */
	@Cacheable(value="testCache")
	public void getNumber(String number){
		System.out.println("查询数据库---2");
	}
	
	
	/**
	 * @CacheEvict 注释来标记要清空缓存的方法，当这个方法被调用后，即会清空缓存。
	 * 注意其中一个 @CacheEvict(value=”testCache”,key=”#account.getName()”)，其中的 Key 是用来指定缓存的 key 的，
	 * 这里因为我们保存的时候用的是 account 对象的 name 字段，所以这里还需要从参数 account 对象中获取 name 的值来作为 key，
	 * 前面的 # 号代表这是一个 SpEL 表达式，此表达式可以遍历方法的参数对象，具体语法可以参考 Spring 的相关文档手册。
	 */
	@CacheEvict(value="testCache",allEntries=true)
	public void updateString(String str){
		
	}
	
	/**
	 * @CachePut 注释，这个注释可以确保方法被执行，同时方法的返回值也被记录到缓存中，实现缓存与数据库的同步更新。
	 * @param number
	 */
	@CachePut(value="testCache")
	public void insertNumber(String number){
		
	}
}
