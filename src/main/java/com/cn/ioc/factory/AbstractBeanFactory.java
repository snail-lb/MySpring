package com.cn.ioc.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cn.ioc.beans.BeanDefinition;
import com.cn.ioc.beans.BeanPostProcessor;

/**
 * 管理bean
 * 
 * @author lvbiao
 *
 */
public abstract class AbstractBeanFactory implements BeanFactory {
	//bean存储池
	Map<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();

	//bean名称列表
	private final List<String> beanDefinitionNames = new ArrayList<String>();
	
	//实现了PostProcessors接口的bean，这部分bean会被先行处理
	private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

	public Object getBean(String name) throws Exception {
		BeanDefinition beanDefinition = beanDefinitionMap.get(name);
		if (beanDefinition == null) {
			throw new IllegalArgumentException("No bean named " + name + " is defined");
		}
		Object bean = beanDefinition.getBean();
		if (bean == null) {
			bean = doCreateBean(beanDefinition);
		} else {
			// 如果bean的作用域为原型，那么在创建一个新的bean返回
			if (beanDefinition.getScope().equals("prototype")) {
				bean = doCreateBean(beanDefinition);
			}
		}
		// 至此bean已经完成实例化，并且属性也已经设置成功
		// 接下来完成bean生命周期中的部分步骤
		// 1.如果bean实现BeanNameAware（此步骤放弃，因为bean定义中一直使用的是beanName，并没有使用beanID）
		// 2.如果bean实现BeanFactoryAware接口，则调用setBeanFactory()方法
		if (bean instanceof BeanFactoryAware) {
			((BeanFactoryAware) bean).setBeanFactory(this);
		}
		
		//设置bean的生命周期
		bean = setBeanLifecycle(bean,name);
		
		return bean;
	}

	public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
		beanDefinitionMap.put(name, beanDefinition);
		beanDefinitionNames.add(name);
	}

	public void preInstantiateSingletons() throws Exception {
		for (Iterator it = this.beanDefinitionNames.iterator(); it.hasNext();) {
			String beanName = (String) it.next();
			getBean(beanName);
		}
	}

	/**
	 * 初始化bean
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;

	/**
	 * 设置bean的生命周期
	 * 
	 * @param bean
	 * @param name
	 */
	public Object setBeanLifecycle(Object bean, String name) throws Exception {
		// 4.如果bean实现了BeanPostProcessor接口，在调用方法postProcessBeforeInitialization()
		if (bean instanceof BeanPostProcessor) {
			bean = ((BeanPostProcessor) bean).postProcessBeforeInitialization(bean, name);
		}
		// 5.如果bean实现了InitializingBean接口则调用方法
		if (bean instanceof InitializingBean) {
			((InitializingBean) bean).afterPropertiesSet();
		}
		// 6.如果bean实现了BeanPostProcessor接口，在调用方法postProcessAfterInitialization()
		if (bean instanceof BeanPostProcessor) {
			bean = ((BeanPostProcessor) bean).postProcessAfterInitialization(bean, name);
		}
		return bean;

	}

	/**
	 * 查找出所有此 Class 对象所表示的类或接口与指定的 Class 参数所表示的类或接口是否相同，或是否是其超类或超接口。
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<Object> getBeansForType(Class<?> type) throws Exception {
		List<Object> beans = new ArrayList<Object>();
		for (String beanDefinitionName : beanDefinitionNames) {
			//判定此 Class 对象所表示的类或接口与指定的 Class 参数所表示的类或接口是否相同，或是否是其超类或超接口。
			if (type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())) {
				beans.add(getBean(beanDefinitionName));
			}
		}
		return beans;
	}

	public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
		this.beanPostProcessors.add(beanPostProcessor);
	}

}
