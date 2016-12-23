package com.cn.aop;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {

	private PointcutParser pointcutParser;//切点解析器

	private String expression;	//用户定义的切点表达式

	private PointcutExpression pointcutExpression;//代表一个切入点表达式，并提供便利的方法来确定是否匹配到指定的连接点

	/**
	 * 被支持的基本单元
	 */
	private static final Set<PointcutPrimitive> DEFAULT_SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();

	static {
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
	}

	public AspectJExpressionPointcut() {
		this(DEFAULT_SUPPORTED_PRIMITIVES);
	}

	/**
	 * 获取切点解析器
	 * @param supportedPrimitives
	 */
	public AspectJExpressionPointcut(Set<PointcutPrimitive> supportedPrimitives) {
		pointcutParser = PointcutParser
				.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(supportedPrimitives);
	}

	/**
	 * 检查是否已经匹配了切入点，或者是否已经解析并设置了pointcutExpression
	 */
	protected void checkReadyToMatch() {
		if (pointcutExpression == null) {
			pointcutExpression = buildPointcutExpression();
		}
	}

	/**
	 * 解析给定的切入点表达式
	 * @return PointcutExpression
	 */
	private PointcutExpression buildPointcutExpression() {
		return pointcutParser.parsePointcutExpression(expression);
	}

	/**
	 * 设置用户定义的切点表达式
	 * @param expression
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}

	public ClassFilter getClassFilter() {
		return this;
	}

	public MethodMatcher getMethodMatcher() {
		return this;
	}

	/**
	 * 类匹配
	 */
	public boolean matches(Class<?> targetClass) {
		checkReadyToMatch();
		return pointcutExpression.couldMatchJoinPointsInType(targetClass);
	}

	/**
	 * 方法匹配
	 */
	public boolean matches(Method method, Class<?> targetClass) {
		checkReadyToMatch();
		ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
		if (shadowMatch.alwaysMatches()) {
			return true;
		} else if (shadowMatch.neverMatches()) {
			return false;
		}
		return false;
	}
}
