package com.cn.ioc.factory;

import java.util.Map;

import org.junit.Test;

import com.cn.ioc.beans.BeanDefinition;
import com.cn.ioc.io.ResourceLoader;
import com.cn.ioc.xml.XmlBeanDefinitionReader;

public class BeanFactoryTest {
	@Test
	public void TestBeanFactory() throws Exception {
		// 1.读取配置
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
		xmlBeanDefinitionReader.loadBeanDefinitions("spring.xml");

		// 2.初始化BeanFactory并注册bean
		BeanFactory beanFactory = new AutowireCapableBeanFactory();
		for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
			//将bean注册到工厂
			beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
		}

		// 3.获取bean
		TestService helloWorldService = (TestService) beanFactory.getBean("testService");
		helloWorldService.sayHello();
	}

}
