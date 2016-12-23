package com.cn.ioc.context;

import com.cn.ioc.beans.BeanPostProcessor;
import com.cn.ioc.factory.AbstractBeanFactory;
import com.cn.ioc.factory.InitializingBean;

public abstract class AbstractApplicationContext implements ApplicationContext {

	protected AbstractBeanFactory beanFactory;

	public AbstractApplicationContext(AbstractBeanFactory abstractBeanFactory) {
		this.beanFactory = abstractBeanFactory;
	}

	public void refresh() throws Exception {
	}

	public Object getBean(String name) throws Exception {
		Object bean = beanFactory.getBean(name);
		// 3.如果bean实现了ApplicationContextAwrae，则调用setApplicationContext方法
		if (bean instanceof ApplicationContextAwrae) {
			((ApplicationContextAwrae) bean).setApplicationContext(this);
		}
		//4.如果bean实现了BeanPostProcessor接口，在调用方法postProcessBeforeInitialization()
		if (bean instanceof BeanPostProcessor) {
			((BeanPostProcessor) bean).postProcessBeforeInitialization(bean,name);
		}
		//5.如果bean实现了InitializingBean接口则调用方法
		if (bean instanceof InitializingBean) {
			((InitializingBean) bean).afterPropertiesSet();
		}
		//4.如果bean实现了BeanPostProcessor接口，在调用方法postProcessAfterInitialization()
		if (bean instanceof BeanPostProcessor) {
			((BeanPostProcessor) bean).postProcessAfterInitialization(bean,name);
		}
		return bean;
	}

}
