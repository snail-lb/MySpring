package com.cn.ioc.beans;

/**
 * 用于解决bean依赖问题
 * @author lvbiao
 *
 */
public class BeanReference {

	private String name;
	
	private Object bean;
	
	public BeanReference(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}
	
	
}
