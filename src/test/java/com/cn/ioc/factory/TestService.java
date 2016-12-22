package com.cn.ioc.factory;

public class TestService {
	
	private String name;
	
	private TestServicePerson person;
	
	public void sayHello(){
		System.out.println(name + " is a " + person.getSex());
	}
	
	public void setName(String name){
		this.name = name;
	}

	public void setPerson(TestServicePerson person) {
		this.person = person;
	}
	
	
}
