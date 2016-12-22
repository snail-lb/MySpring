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
