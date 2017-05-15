package com.transaction.annotaion;

import java.lang.reflect.Proxy;

/**
 * 动态代理
 * @author Mark
 *
 */
public class TransactionProxy {
	
	public static Object proxyFor(Object obj){
		return Proxy.newProxyInstance(
				obj.getClass().getClassLoader(), 
				obj.getClass().getInterfaces(), 
				new TransactionInvocationHandler(obj));
	}
}
