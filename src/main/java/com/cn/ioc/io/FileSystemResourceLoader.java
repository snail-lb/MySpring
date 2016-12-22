package com.cn.ioc.io;

import java.io.File;

public class FileSystemResourceLoader implements ResourceLoader{
	public Resource getResource(String filePath){
		return new FileSystemResource(new File(filePath));
	}
}
