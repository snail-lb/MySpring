package com.cn.ioc.factory;

public class TestServiceImpl implements TestService{
	private String name;

	public void sayHello() {
		System.out.println(name + " sayHello");
	}

	public void setName(String name) {
		this.name = name;
	}

}
