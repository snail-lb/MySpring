package com.cn.ioc.factory;

import com.cn.ioc.beans.BeanDefinition;

public interface BeanFactory {
	Object getBean(String name) throws Exception;
	
	void registerBeanDefinition(String name,BeanDefinition beanDefinition);

}
