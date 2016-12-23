package com.cn.ioc.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cn.ioc.beans.BeanDefinition;

/**
 * 管理bean
 * @author lvbiao
 *
 */
public abstract class AbstractBeanFactory implements BeanFactory{
	Map<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();
	
	private final List<String> beanDefinitionNames = new ArrayList<String>();

	public Object getBean(String name) throws Exception {
		BeanDefinition beanDefinition = beanDefinitionMap.get(name);
		if(beanDefinition == null){
			throw new IllegalArgumentException("No bean named " + name + " is defined");
		}
		Object bean = beanDefinition.getBean();
		if (bean == null) {
			bean = doCreateBean(beanDefinition);
		}else{
			//如果bean的作用域为原型，那么在创建一个新的bean返回
			if(beanDefinition.getScope().equals("prototype")){
				bean = doCreateBean(beanDefinition);
			}
		}
		//至此bean已经完成实例化，并且属性也已经设置成功
		//接下来完成bean生命周期中的部分步骤
		//1.如果bean实现BeanNameAware（此步骤放弃，因为bean定义中一直使用的是beanName，并没有使用beanID）
		//2.如果bean实现BeanFactoryAware接口，则调用setBeanFactory()方法
		if (bean instanceof BeanFactoryAware) {
			((BeanFactoryAware) bean).setBeanFactory(this);
		}
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
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;
	
}
