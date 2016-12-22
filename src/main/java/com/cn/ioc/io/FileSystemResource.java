package com.cn.ioc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileSystemResource implements Resource{
	private final File file;

	public FileSystemResource(File file){
		this.file = file;
	}

	public InputStream getInputStream() throws IOException {
		FileInputStream fileInputStream = new FileInputStream(file);
		return fileInputStream;
	}
}
