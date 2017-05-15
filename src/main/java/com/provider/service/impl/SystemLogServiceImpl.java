package com.provider.service.impl;


import org.springframework.stereotype.Service;

import com.provider.service.SystemLogService;



@Service("systemLogService")
public class SystemLogServiceImpl implements SystemLogService {


	@Override
	public int deleteSystemLog(String id) {
		System.out.println("删除日志");
		return 0;
	}



}
