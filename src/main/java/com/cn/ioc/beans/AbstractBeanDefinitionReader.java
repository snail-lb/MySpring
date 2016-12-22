package com.cn.ioc.beans;

import java.util.HashMap;
import java.util.Map;

import com.cn.ioc.io.ResourceLoader;


public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

	private Map<String , BeanDefinition> registry;//用于存储bean
	
	private ResourceLoader resourceLoacer;//用于查找spring文件
	
	protected AbstractBeanDefinitionReader(ResourceLoader rl){
		this.registry = new HashMap<String, BeanDefinition>();
		this.resourceLoacer = rl;
	}
	
	public Map<String, BeanDefinition> getRegistry(){
		return registry;
	}
	
	public ResourceLoader getResourceLoader(){
		return resourceLoacer;
	}
	
}
