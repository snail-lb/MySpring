package com.cn.aop;

/**
 * 类匹配接口
 * @author lvbiao
 *
 */
public interface ClassFilter {

	/**
	 * 匹配类
	 * @param targetClass
	 * @return
	 */
	boolean matches(Class<?> targetClass);
	
}
