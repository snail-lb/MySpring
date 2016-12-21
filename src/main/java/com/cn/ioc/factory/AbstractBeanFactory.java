package com.cn.ioc.factory;

import java.util.HashMap;
import java.util.Map;

import com.cn.ioc.beans.BeanDefinition;

public abstract class AbstractBeanFactory implements BeanFactory{
	Map<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();

	public Object getBean(String name) {
		return beanDefinitionMap.get(name).getBean();
	}

	public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
		Object bean  = doCreateBean(beanDefinition);
		beanDefinition.setBean(bean);
		beanDefinitionMap.put(name, beanDefinition);
	}

	/**
	 * 初始化bean
	 * @param name
	 * @return
	 */
	protected abstract Object doCreateBean(BeanDefinition beanDefinition);
	
}
