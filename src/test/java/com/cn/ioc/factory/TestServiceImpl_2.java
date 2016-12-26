package com.cn.ioc.factory;

public class TestServiceImpl_2 implements TestService{
	private String name;

	public void sayHello() {
		System.out.println(name + " sayHello");
	}
}
