package com.cn.aop;

import org.aopalliance.aop.Advice;

public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

	/**
	 * 语法分析,获取切点，即配置的的类，方法（切入点）
	 */
    private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

    /**
     * 通知
     */
    private Advice advice;

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.pointcut.setExpression(expression);
    }

	public Advice getAdvice() {
		return advice;
	}

	public Pointcut getPointcut() {
		return pointcut;
	}
}
