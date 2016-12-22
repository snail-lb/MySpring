package com.cn.ioc.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 用于加载spring配置文件
 * @author lvbiao
 *
 */
public class UrlResource implements Resource{
	
	private final URL url;
	
	public UrlResource(URL url){
		this.url = url;
	}
	
	public InputStream getInputStream() throws IOException {
		URLConnection urlConnection = url.openConnection();
		urlConnection.connect();
		return urlConnection.getInputStream();
	}

}
