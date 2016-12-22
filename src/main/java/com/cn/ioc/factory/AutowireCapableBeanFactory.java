package com.cn.ioc.factory;

import java.lang.reflect.Field;

import com.cn.ioc.beans.BeanDefinition;
import com.cn.ioc.beans.BeanReference;
import com.cn.ioc.beans.PropertyValue;

/**
 * 在这里进行bean的创建,并使用反射设置属性
 * @author lvbiao
 *
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory{

	@Override
	protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
		//创建Bean
		Object bean = createBeanInstance(beanDefinition);
		beanDefinition.setBean(bean);
		//设置Bean中的属性
		applyPropertyValues(bean, beanDefinition);
		return bean;
	}

	/**
	 * 通过反射设置bean中的属性
	 * @param bean
	 * @param beanDefinition
	 * @throws Exception 
	 */
	protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception{
		for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
			Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
			declaredField.setAccessible(true);
			Object value = propertyValue.getValue();
			//bean依赖,如果这个属性为BeanRefence，说明这个属性值为bean
			if (value instanceof BeanReference) {
				BeanReference beanReference = (BeanReference) value;
				value = getBean(beanReference.getName());
			}
			declaredField.set(bean, value);
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
