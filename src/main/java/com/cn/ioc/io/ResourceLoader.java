package com.cn.ioc.io;

/**
 * 查找具有给定名称的资源,在这里就是查找指定的spring配置文件
 * @author lvbiao
 *
 */
public interface ResourceLoader {
	public Resource getResource(String location);
}
