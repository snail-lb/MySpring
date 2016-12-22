package com.cn.ioc.xml;

import java.util.Map;

import org.junit.Test;

import com.cn.ioc.beans.BeanDefinition;
import com.cn.ioc.io.ResourceLoader;

import junit.framework.Assert;

public class XmlBeanDefinitionReaderTest {
	
	@Test
	public void test() throws Exception{
		XmlBeanDefinitionReader xbdr = new XmlBeanDefinitionReader(new ResourceLoader());
		xbdr.loadBeanDefinitions("spring.xml");
		Map<String, BeanDefinition> registry = xbdr.getRegistry();
		Assert.assertTrue(registry.size() > 0);
	}
	
}
