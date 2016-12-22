package com.cn.ioc.context;

import java.io.File;
import java.util.Map;

import com.cn.ioc.beans.BeanDefinition;
import com.cn.ioc.factory.AbstractBeanFactory;
import com.cn.ioc.factory.AutowireCapableBeanFactory;
import com.cn.ioc.io.FileSystemResourceLoader;
import com.cn.ioc.xml.XmlBeanDefinitionReader;

public class FileSystemXmlApplicationContext extends AbstractApplicationContext{
	private String filePath;

	public FileSystemXmlApplicationContext(String filePath) throws Exception {
		this(filePath, new AutowireCapableBeanFactory());
	}
	
	public FileSystemXmlApplicationContext(String filePath, AbstractBeanFactory beanFactory) throws Exception{
		super(beanFactory);
		this.filePath = filePath;
		refresh();
	}
	
	public void refresh() throws Exception{
		XmlBeanDefinitionReader xbdr = new XmlBeanDefinitionReader(new FileSystemResourceLoader());
		xbdr.loadBeanDefinitions(filePath);
		for(Map.Entry<String, BeanDefinition> bdr : xbdr.getRegistry().entrySet()) {
			beanFactory.registerBeanDefinition(bdr.getKey(), bdr.getValue());
		}
	}
}
