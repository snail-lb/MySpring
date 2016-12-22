package com.cin.ioc.io;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.cn.ioc.io.FileSystemResourceLoader;
import com.cn.ioc.io.Resource;
import com.cn.ioc.io.ResourceLoader;
import com.cn.ioc.io.UrlResourceLoader;

import junit.framework.Assert;

public class ResourceLoaderTest {

	@Test
	public void testURL() throws IOException{
		ResourceLoader resourceLoader = new UrlResourceLoader();
		Resource resource = resourceLoader.getResource("spring.xml");
		InputStream inputStream = resource.getInputStream();
		Assert.assertNotNull(inputStream);
	}
	
	@Test
	public void testFileSystem() throws IOException{
		ResourceLoader resourceLoader = new FileSystemResourceLoader();
		Resource resource = resourceLoader.getResource("F:\\mavenProject\\MySpring\\Test\\spring.xml");
		InputStream inputStream = resource.getInputStream();
		Assert.assertNotNull(inputStream);
	}
}
