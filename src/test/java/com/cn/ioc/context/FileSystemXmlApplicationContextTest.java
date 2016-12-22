package com.cn.ioc.context;

import org.junit.Test;

import com.cn.ioc.factory.TestService;

public class FileSystemXmlApplicationContextTest {

	@Test
	public void test(){
		try {
			FileSystemXmlApplicationContext cpac = new FileSystemXmlApplicationContext("F:\\mavenProject\\MySpring\\Test\\spring.xml");
			TestService testService = (TestService) cpac.getBean("testService");
			testService.setName("Tom");
			testService.sayHello();
			
			TestService testService2 = (TestService) cpac.getBean("testService");
			testService2.sayHello();
			
			System.out.println(testService == testService2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
