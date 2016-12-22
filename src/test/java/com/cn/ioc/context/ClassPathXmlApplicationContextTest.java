package com.cn.ioc.context;

import org.junit.Test;

import com.cn.ioc.factory.TestService;

public class ClassPathXmlApplicationContextTest {
	
	@Test
	public void test(){
		try {
			ClassPathXmlApplicationContext cpac = new ClassPathXmlApplicationContext("spring.xml");
			TestService testservice = (TestService) cpac.getBean("tstService");
			testservice.sayHello();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
