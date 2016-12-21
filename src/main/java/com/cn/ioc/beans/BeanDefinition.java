package com.cn.ioc.beans;


public class BeanDefinition {
	private String beanClassName;
	
	private Class<?> beanClass; 
	
	private Object bean;
	
	private PropertyValues propertyValues;

	public String getBeanClassName() {
		return beanClassName;
	}

	public void setBeanClassName(String beanClassName) {
		this.beanClassName = beanClassName;
		Class<?> beanClass;
		try {
			beanClass = Class.forName(beanClassName);
			this.beanClass = beanClass;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Class<?> getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class<?> beanClass) {
			this.beanClass = beanClass;
	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	public PropertyValues getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(PropertyValues propertyValues) {
		this.propertyValues = propertyValues;
	}
	
}
