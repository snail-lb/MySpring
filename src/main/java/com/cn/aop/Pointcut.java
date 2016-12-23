package com.cn.aop;

/**
 * 切入点接口
 * @author lvbiao
 *
 */
public interface Pointcut {
	
	/**
	 * 获取匹配到的类
	 * @return ClassFilter
	 */
	ClassFilter getClassFilter();

	/**
	 * 获取匹配到的方法
	 * @return MethodMatcher
	 */
	MethodMatcher getMethodMatcher();
}
