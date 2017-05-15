package com.rmi.service.impl;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import com.entity.Person;
import com.rmi.service.ServerInter;

/**
 * 远程服务的实现
 * 1：继承UnicastRemoteObject类
 * 2：显示写出无参构造器，同时抛出RemoteException异常
 * 
 * @author Mark
 *
 */
public class ServiceImpl extends UnicastRemoteObject implements ServerInter {

	//显示无参构造，抛出异常
	protected ServiceImpl() throws RemoteException {
	}

	@Override
	public String say(String name) throws RemoteException {
		
		return name + "��ã���ӭ��";
	}

	@Override
	public Person getPerson(String name, int age) throws RemoteException {
		
		return new Person(name,age);
	}
	
	//服务端本地方法，该方法只能被JVM本地调用
	public void info(){
		System.out.println("�Ź���");
	}
	
	
	//测试方法
	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
		//����Զ�̷���ʵ��
		ServerInter ser = new ServiceImpl();
		//ע��Զ�̷���˿�
		LocateRegistry.createRegistry(1099);
		//��Զ�̷���ʵ����ΪԶ�̷���
		Naming.rebind("rmi://localhost:1099/mark", ser);
	}
	
	
	
}
