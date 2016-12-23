package com.cn.ioc.factory;


public interface BeanFactoryAware {
	void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
