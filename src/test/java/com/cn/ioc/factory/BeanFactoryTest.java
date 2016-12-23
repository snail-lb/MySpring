package com.cn.ioc.factory;

import java.util.Map;

import org.junit.Test;

import com.cn.ioc.beans.BeanDefinition;
import com.cn.ioc.io.ResourceLoader;
import com.cn.ioc.io.UrlResourceLoader;
import com.cn.ioc.xml.XmlBeanDefinitionReader;

public class BeanFactoryTest {
	@Test
	public void TestBeanFactory() throws Exception {
		// 1.读取配置
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new UrlResourceLoader());
		xmlBeanDefinitionReader.loadBeanDefinitions("spring.xml");

		// 2.初始化BeanFactory并注册bean
		AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
		for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
			//将bean注册到工厂
			beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
		}

		// 3.初始化bean
        beanFactory.preInstantiateSingletons();
        
		// 4.获取bean
        TestServiceImpl testServiceImpl = (TestServiceImpl) beanFactory.getBean("testService");
		
        testServiceImpl.sayHello();
	}

}
