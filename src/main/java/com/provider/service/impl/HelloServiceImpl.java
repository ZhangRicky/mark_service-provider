package com.provider.service.impl;

import org.springframework.stereotype.Service;

import com.aopLog.Log;
import com.provide.service.HelloProviderService;



/**
 * ʵ��base-component��Ŀ���еĽӿ�
 * @author Mark
 *
 */
@Service("helloService")
public class HelloServiceImpl implements HelloProviderService{

	@Override
	@Log(opType = "add操作:", opText = "添加用户", appModule = "")
	public String getHello() {
		System.out.println("Hello World");
		return "";
	}

}
