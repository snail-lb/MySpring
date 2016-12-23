package com.cn.ioc.context;

import org.junit.Test;

import com.cn.ioc.factory.TestService;

public class ClassPathXmlApplicationContextTest {
	
	@Test
	public void test(){
		try {
			ClassPathXmlApplicationContext cpac = new ClassPathXmlApplicationContext("spring.xml");
			TestService testService = (TestService) cpac.getBean("testService");
			testService.sayHello();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
