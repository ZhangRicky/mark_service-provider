package com.provider.service.impl;

import org.springframework.stereotype.Service;

import com.entity.Person;
import com.provide.service.PersonProviderService;

public class PersonServiceImpl implements PersonProviderService {

	public Person getPersonInfo() {
		Person p =new Person();
		p.setId("0001");
		p.setName("Ricky");
		p.setAge(25);
		p.setSex("男");
		p.setPhone("18681089009");
		return p;
	}

}
