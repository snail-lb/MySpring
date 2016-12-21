package com.cn.ioc.factory;

import org.junit.Test;

import com.cn.ioc.beans.BeanDefinition;
import com.cn.ioc.beans.PropertyValue;
import com.cn.ioc.beans.PropertyValues;

public class BeanFactoryTest {
	@Test
	public void TestBeanFactory(){
		//1.初始化beanFactory
		BeanFactory beanFactory = new AutowireCapableBeanFactory();
		//2.bean定义
		BeanDefinition beanDefinition = new BeanDefinition();
		beanDefinition.setBeanClassName("com.cn.ioc.factory.TestService");
		//3.设置属性
		PropertyValue propertyValue = new PropertyValue("name", "Tom");
		PropertyValues propertyValues = new PropertyValues();
		propertyValues.addPropertyValue(propertyValue);
		beanDefinition.setPropertyValues(propertyValues);
		//4.生成bean
		beanFactory.registerBeanDefinition("testService", beanDefinition);
		//5.获取bean
		TestService testService = (TestService) beanFactory.getBean("testService");
		testService.sayHello();
	}
	
}
