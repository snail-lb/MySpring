package com.cn.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.aopalliance.intercept.MethodInterceptor;

public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler {

	public JdkDynamicAopProxy(AdvisedSupport advised) {
		super(advised);
	}

	//获取代理对象
	public Object getProxy() {
		return Proxy.newProxyInstance(getClass().getClassLoader(),
				new Class[] { advised.getTargetSource().getTargetClass() }, this);
	}

	//执行目标对象的方法
	public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
		MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
		ReflectiveMethodInvocation rmi = new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method, args);
		return methodInterceptor.invoke(rmi);
	}

}
