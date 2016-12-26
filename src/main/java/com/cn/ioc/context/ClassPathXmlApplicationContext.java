package com.cn.ioc.context;

import java.util.Map;

import com.cn.ioc.beans.BeanDefinition;
import com.cn.ioc.factory.AbstractBeanFactory;
import com.cn.ioc.factory.AutowireCapableBeanFactory;
import com.cn.ioc.io.UrlResourceLoader;
import com.cn.ioc.xml.XmlBeanDefinitionReader;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext{
	
	private String configLocation;

	public ClassPathXmlApplicationContext(String configLocation) throws Exception {
		this(configLocation, new AutowireCapableBeanFactory());
	}
	
	public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception{
		super(beanFactory);
		this.configLocation = configLocation;
		refresh();
	}
	
	/*public void refresh() throws Exception{
		XmlBeanDefinitionReader xbdr = new XmlBeanDefinitionReader(new UrlResourceLoader());
		xbdr.loadBeanDefinitions(configLocation);
		for(Map.Entry<String, BeanDefinition> bdr : xbdr.getRegistry().entrySet()) {
			beanFactory.registerBeanDefinition(bdr.getKey(), bdr.getValue());
		}
	}*/

	@Override
	protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
		XmlBeanDefinitionReader xbdr = new XmlBeanDefinitionReader(new UrlResourceLoader());
		xbdr.loadBeanDefinitions(configLocation);
		for(Map.Entry<String, BeanDefinition> bdr : xbdr.getRegistry().entrySet()) {
			beanFactory.registerBeanDefinition(bdr.getKey(), bdr.getValue());
		}
	}
}
