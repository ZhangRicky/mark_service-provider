package com.rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.entity.Person;



/**
 * RMI服务接口
 * 
 * 1：远程服务接口必须继承Romote接口
 * @author Mark
 *
 */
public interface ServerInter extends Remote {
	
	public String say(String name) throws RemoteException;
	
	public Person getPerson(String name,int age) throws RemoteException;
}
