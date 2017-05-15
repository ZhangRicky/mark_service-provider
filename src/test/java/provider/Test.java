package provider;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.provide.service.HelloProviderService;
import com.provide.service.PersonProviderService;

import com.redis.cache.RedisCacheTestServiceImpl;

public class Test {
	
	 
	/**
	 * 测试缓存数据
	 * @param args
	 */
    public static void main(String[] args) {  
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(    
                new String[]{"classpath:applicationContext.xml"});    
        context.start();   
        //获取Bean
        RedisCacheTestServiceImpl cache = (RedisCacheTestServiceImpl) context.getBean("redisCacheService");
        cache.getNumber("1");
  
    }  
	 
}

