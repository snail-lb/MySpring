package com.cn.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class TestAspect {
	public void doAfter() {
		System.out.println("doAfter方法");
	}

	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		long time = System.currentTimeMillis();
		Object retVal = pjp.proceed();
		time = System.currentTimeMillis() - time;
		System.out.println("doAround方法   process time: " + time + " ms");
		return retVal;
	}

	public void doBefore() {
		System.out.println("doBefore方法");
	}

	public void doThrowing() {
		System.out.println("异常处理方法");
	}
}
