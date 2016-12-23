package com.cn.aop;

import org.junit.Test;

import com.cn.ioc.factory.TestServiceImpl;

import junit.framework.Assert;

public class AspectJExpressionPointcutTest {

	@Test
	public void testClassFilter(){
		String expression = "execution (* com.cn.ioc.factory.TestServiceImpl.*(..))";
		AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
		aspectJExpressionPointcut.setExpression(expression);
		boolean matches = aspectJExpressionPointcut.getClassFilter().matches(TestServiceImpl.class);
		Assert.assertTrue(matches);
	}
	
	@Test
	public void testMethodInterceptor() throws NoSuchMethodException, SecurityException{
		String expression = "execution (* com.cn.ioc.factory.TestServiceImpl.*(..))";
		AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
		aspectJExpressionPointcut.setExpression(expression);
		boolean matches = aspectJExpressionPointcut.getMethodMatcher().matches(TestServiceImpl.class.getDeclaredMethod("sayHello"), TestServiceImpl.class);
		Assert.assertTrue(matches);
	}
}

