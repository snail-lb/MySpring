package com.cn.aop;

import org.aopalliance.aop.Advice;

/**
 * 通知接口
 * @author lvbiao
 *
 */
public interface Advisor {
	Advice getAdvice();
}
