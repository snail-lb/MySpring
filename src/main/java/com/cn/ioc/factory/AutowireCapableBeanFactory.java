package com.cn.ioc.factory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.cn.ioc.beans.BeanDefinition;
import com.cn.ioc.beans.BeanPostProcessor;
import com.cn.ioc.beans.BeanReference;
import com.cn.ioc.beans.PropertyValue;

/**
 * 在这里进行bean的创建,并使用反射设置属性
 * 
 * @author lvbiao
 *
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory  {

	@Override
	protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
		// 创建Bean
		Object bean = createBeanInstance(beanDefinition);
		beanDefinition.setBean(bean);
		// 设置Bean中的属性
		applyPropertyValues(bean, beanDefinition);
		return bean;
	}

	/**
	 * 通过反射设置bean中的属性
	 * 
	 * @param bean
	 * @param beanDefinition
	 * @throws Exception
	 */
	protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
		for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
			Object value = propertyValue.getValue();
			// bean依赖,如果这个属性为BeanRefence，说明这个属性值为bean
			if (value instanceof BeanReference) {
				BeanReference beanReference = (BeanReference) value;
				value = getBean(beanReference.getName());
			}

			// 先通过set方法进行调用，如果没有set方法，在使用反射直接操作属性
			try {
				Method declaredMethod = bean.getClass().getDeclaredMethod("set"
						+ propertyValue.getName().substring(0, 1).toUpperCase() + propertyValue.getName().substring(1),
						value.getClass());
				declaredMethod.setAccessible(true);

				declaredMethod.invoke(bean, value);
			} catch (NoSuchMethodException e) {
				Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
				declaredField.setAccessible(true);
				declaredField.set(bean, value);
			}

		}

	}

	/**
	 * 实例化bean
	 * 
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
