package com.cn.ioc.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 内部资源定位接口
 * @author lvbiao
 *
 */
public interface Resource {
	InputStream getInputStream() throws IOException;
}
