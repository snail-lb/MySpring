package com.cn.aop;

import org.junit.Test;

import com.cn.ioc.context.ApplicationContext;
import com.cn.ioc.context.ClassPathXmlApplicationContext;
import com.cn.ioc.factory.TestService;

public class JdkDynamicAopProxyTest {

	@Test
	public void testInterceptor() throws Exception {
		// --------- TestService without AOP
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
		TestService TestService = (TestService) applicationContext.getBean("testService");
		TestService.sayHello();
		
		System.out.println("=========================");

		// --------- TestService with AOP
		// 1. 设置被代理对象(Joinpoint)
		AdvisedSupport advisedSupport = new AdvisedSupport();
		
		TargetSource targetSource = new TargetSource(TestService, TestService.class);
		advisedSupport.setTargetSource(targetSource);

		// 2. 设置拦截器(Advice)
		TimerInterceptor timerInterceptor = new TimerInterceptor();
		advisedSupport.setMethodInterceptor(timerInterceptor);

		// 3. 创建代理(Proxy)
		JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
		TestService TestServiceProxy = (TestService) jdkDynamicAopProxy.getProxy();

        // 4. 基于AOP的调用
        TestServiceProxy.sayHello();

	}
}
