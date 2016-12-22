package com.cn.ioc.io;

import java.net.URL;

/**
 * 查找具有给定名称的资源,在这里就是查找指定的spring配置文件
 * @author lvbiao
 *
 */
public class ResourceLoader {
	public Resource getResource(String location){
		URL resource = this.getClass().getClassLoader().getResource(location);
		return new UrlResource(resource);
	}

}
