package com.cn.aop;

public interface PointcutAdvisor extends Advisor{
	
	Pointcut getPointcut();
	
}
