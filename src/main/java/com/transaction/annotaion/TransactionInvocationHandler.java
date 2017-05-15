package com.transaction.annotaion;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;



import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.config.TransactionManagementConfigUtils;






public class TransactionInvocationHandler implements InvocationHandler {
	
	private Object proxy;	//代理对象
	

	public TransactionInvocationHandler(Object obj) {
		this.proxy =obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Method originalMethod =proxy.getClass().getMethod(method.getName(), method.getParameterTypes());
		//判断方法是否有注解
		if(!originalMethod.isAnnotationPresent(MyTransaction.class)){
			//没有注解直接调用方法
			return method.invoke(proxy, args);
		}
		TransactionManager.beginTransaction();
		Object result = null;
		try {
			result = method.invoke(proxy, args);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
		} finally {
			TransactionManager.close();
		}
		return result;
	}

}
