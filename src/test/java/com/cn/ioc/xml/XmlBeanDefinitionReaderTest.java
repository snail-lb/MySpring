package com.cn.ioc.xml;

import java.util.Map;

import org.junit.Test;

import com.cn.ioc.beans.BeanDefinition;
import com.cn.ioc.io.FileSystemResourceLoader;
import com.cn.ioc.io.ResourceLoader;
import com.cn.ioc.io.UrlResourceLoader;

import junit.framework.Assert;

public class XmlBeanDefinitionReaderTest {
	
	@Test
	public void testUrl() throws Exception{
		XmlBeanDefinitionReader xbdr = new XmlBeanDefinitionReader(new UrlResourceLoader());
		xbdr.loadBeanDefinitions("spring.xml");
		Map<String, BeanDefinition> registry = xbdr.getRegistry();
		Assert.assertTrue(registry.size() > 0);
	}
	
	@Test
	public void testFileSystem() throws Exception{
		XmlBeanDefinitionReader xbdr = new XmlBeanDefinitionReader(new FileSystemResourceLoader());
		xbdr.loadBeanDefinitions("F:\\mavenProject\\MySpring\\Test\\spring.xml");
		Map<String, BeanDefinition> registry = xbdr.getRegistry();
		Assert.assertTrue(registry.size() > 0);
	}
	
}
