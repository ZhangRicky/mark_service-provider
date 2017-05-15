package com.aopLog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.provider.service.impl.HelloServiceImpl;




/**
 * 測試
 * 
 * @author Mark
 *
 */


public class TestLog {

	@Test
	public void testAOP1() {
		// 启动Spring容器
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				new String[] { "classpath:applicationContext.xml"});
		// 获取service或controller组件
		HelloServiceImpl userService = (HelloServiceImpl) ctx.getBean("helloService");
		userService.getHello();
	}
}
