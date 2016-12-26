package com.cn.ioc.context;

import org.junit.Test;

import com.cn.aop.AopProxy;
import com.cn.aop.JdkDynamicAopProxy;
import com.cn.ioc.factory.TestService;

public class ClassPathXmlApplicationContextTest {
	
	@Test
	public void test(){
		try {
			ClassPathXmlApplicationContext cpac = new ClassPathXmlApplicationContext("spring.xml");
			TestService testService = (TestService) cpac.getBean("testService");
			testService.sayHello();
//			System.out.println(testService instanceof AopProxy);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
