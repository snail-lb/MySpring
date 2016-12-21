package com.cn.ioc.factory;

import java.lang.reflect.Field;

import com.cn.ioc.beans.BeanDefinition;
import com.cn.ioc.beans.PropertyValue;

public class AutowireCapableBeanFactory extends AbstractBeanFactory{

	@Override
	protected Object doCreateBean(BeanDefinition beanDefinition) {
		//创建Bean
		Object bean = createBeanInstance(beanDefinition);
		//设置Bean中的属性
		applyPropertyValues(bean, beanDefinition);
		return bean;
	}

	/**
	 * 设置bean中的属性
	 * @param bean
	 * @param beanDefinition
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) {
		for(PropertyValue pv : beanDefinition.getPropertyValues().getPropertyValues()){
			try {
				Field declaredField = bean.getClass().getDeclaredField(pv.getName());
				declaredField.setAccessible(true);
				declaredField.set(bean,pv.getValue());
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * 实例化bean
	 * @param beanDefinition
	 * @return
	 */
	protected Object createBeanInstance(BeanDefinition beanDefinition) {
		try {
			Object bean = beanDefinition.getBeanClass().newInstance();
			return bean;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}


}
