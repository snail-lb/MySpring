package com.cn.ioc.factory;

public interface BeanFactory {
	Object getBean(String name) throws Exception;
}
