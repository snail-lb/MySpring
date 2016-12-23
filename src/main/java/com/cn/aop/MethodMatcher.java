package com.cn.aop;

import java.lang.reflect.Method;

/**
 * 方法匹配接口
 * @author lvbiao
 *
 */
public interface MethodMatcher {

	/**
	 * 匹配方法
	 * @param method
	 * @param targetClass
	 * @return
	 */
	 boolean matches(Method method, Class<?> targetClass);
	
}
