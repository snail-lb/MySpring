package com.cin.ioc.io;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.cn.ioc.io.Resource;
import com.cn.ioc.io.ResourceLoader;

import junit.framework.Assert;

public class ResourceLoaderTest {

	@Test
	public void test() throws IOException{
		ResourceLoader resourceLoader = new ResourceLoader();
		Resource resource = resourceLoader.getResource("spring.xml");
		InputStream inputStream = resource.getInputStream();
		Assert.assertNotNull(inputStream);
	}
}
