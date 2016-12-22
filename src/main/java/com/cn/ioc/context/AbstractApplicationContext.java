package com.cn.ioc.context;

import com.cn.ioc.factory.AbstractBeanFactory;

public abstract class AbstractApplicationContext implements ApplicationContext {

	protected AbstractBeanFactory beanFactory;

	public AbstractApplicationContext(AbstractBeanFactory abstractBeanFactory) {
		this.beanFactory = abstractBeanFactory;
	}

	public void refresh() throws Exception {
	}

	public Object getBean(String name) throws Exception {
		return beanFactory.getBean(name);
	}

}
