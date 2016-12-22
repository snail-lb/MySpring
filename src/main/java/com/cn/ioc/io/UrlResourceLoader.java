package com.cn.ioc.io;

import java.net.URL;

//RUL类型
public class UrlResourceLoader implements ResourceLoader{
	public Resource getResource(String location){
		URL resource = this.getClass().getClassLoader().getResource(location);
		return new UrlResource(resource);
	}
}
