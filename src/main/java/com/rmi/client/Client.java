package com.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.Scanner;

import com.entity.Person;
import com.rmi.service.ServerInter;


/**
 * RMI客户端
 * @author Mark
 *
 */
public class Client {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		
		ServerInter ser =(ServerInter) Naming.lookup("rmi://localhost:1099/mark");
		
		Scanner in=new Scanner(System.in);
		System.out.println("请输入:");
		String inName =in.next();
		
		String name =ser.say(inName);
		System.out.println(name);
	
	
		Person p = ser.getPerson(inName, 25);
		System.out.println(p.toString());
		
		
	}
}
