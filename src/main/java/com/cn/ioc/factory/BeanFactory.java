package com.cn.ioc.factory;

import com.cn.ioc.beans.BeanDefinition;

public interface BeanFactory {
	Object getBean(String name);
	
	void registerBeanDefinition(String name,BeanDefinition beanDefinition);
}
