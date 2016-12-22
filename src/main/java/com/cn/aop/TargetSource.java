package com.cn.aop;

/**
 * 被代理对象
 * @author lvbiao
 *
 */
public class TargetSource {

	private Class<?> targetClass;
	
	private Object target;

	public TargetSource(Object target,Class<?> targetClass) {
		this.targetClass = targetClass;
		this.target = target;
	}

	public Class<?> getTargetClass() {
		return targetClass;
	}

	public void setTargetClass(Class<?> targetClass) {
		this.targetClass = targetClass;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}
	
	
}
