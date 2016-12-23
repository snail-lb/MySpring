package com.cn.ioc.factory;

import com.cn.ioc.beans.BeanPostProcessor;

public class TestServiceImpl implements TestService,BeanFactoryAware,BeanPostProcessor,InitializingBean{
	private String name;
	private BeanFactory beanFactory;

	public void sayHello() {
		System.out.println(name + " sayHello");
	}
	
	public void setName(String name) {
		System.out.println("设置Bean---");
		this.name = name;
	}



	public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
		System.out.println("调用postProcessBeforeInitialization()方法");
		return bean;
	}
	
	public void afterPropertiesSet() throws Exception {
		System.out.println("调用afterPropertiesSet()");
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
		System.out.println("调用postProcessAfterInitialization()方法");
		return bean;
	}

	public void setBeanFactory(BeanFactory beanFactory) throws Exception {
		System.out.println("获取bean工厂");
		this.beanFactory = beanFactory;
	}

}
