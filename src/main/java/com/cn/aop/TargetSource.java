package com.cn.aop;

/**
 * 被代理对象
 * @author lvbiao
 *
 */
public class TargetSource {

	private Class<?> targetClass;
	
	private Class<?>[] interfaces;
	
	private Object target;

	public TargetSource(Object target,Class<?>... interfaces) {
		this.interfaces = interfaces;
		this.target = target;
	}
	
	public TargetSource(Object target, Class<?> targetClass) {
		this.target = target;
		this.targetClass = targetClass;
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

	public Class<?>[] getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(Class<?>[] interfaces) {
		this.interfaces = interfaces;
	}
}
