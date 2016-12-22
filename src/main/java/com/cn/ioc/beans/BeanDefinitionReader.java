package com.cn.ioc.beans;

/**
 * 
 * @author lvbiao
 *
 */
public interface BeanDefinitionReader {
	void loadBeanDefinitions(String Location) throws  Exception;
}
