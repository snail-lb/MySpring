package com.cn.ioc.factory;

public class TestService {
	
	private String name;
	
	public void sayHello(){
		System.out.println("hello, " + name);
	}
	
	public void setName(String name){
		this.name = name;
	}
}
