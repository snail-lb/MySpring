package com.cn.ioc.factory;

public interface InitializingBean {
	void afterPropertiesSet() throws Exception;
}
