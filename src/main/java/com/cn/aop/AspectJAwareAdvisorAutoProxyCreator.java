package com.cn.aop;

import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;

import com.cn.ioc.beans.BeanPostProcessor;
import com.cn.ioc.factory.AbstractBeanFactory;
import com.cn.ioc.factory.BeanFactory;
import com.cn.ioc.factory.BeanFactoryAware;

/**
 * 
 * @author lvbiao
 *
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

	private AbstractBeanFactory beanFactory;

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
		return bean;
	}

	//返回代理bean
	public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
		//如果已经是代理类类型，则直接返回 
		if (bean instanceof AspectJExpressionPointcutAdvisor) {
			return bean;
		}
        if (bean instanceof MethodInterceptor) {
            return bean;
        }
        
        /**
         * 
         * 查找继承了AspectJExpressionPointcutAdvisor的bean,在这里是为了获取
         * 已经配置了的aspectjAspect即
         * <bean id="aspectjAspect" class="com.cn.aop.AspectJExpressionPointcutAdvisor">
         *		<property name="advice" ref="timeInterceptor"></property>
         * 		<property name="expression" value="execution(* com.cn.ioc.factory.*.*(..))"></property>
    	 *	</bean>
         */
		List<Object> advisors = beanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);
		for (Object advisor : advisors) {
			AspectJExpressionPointcutAdvisor advisorAsp = (AspectJExpressionPointcutAdvisor)advisor;
			
			if (advisorAsp.getPointcut().getClassFilter().matches(bean.getClass())) {
				//如果这个bean被配置为切入点，则新建一个代理相关的数据源
				AdvisedSupport advisedSupport = new AdvisedSupport();
				//设置属性
				advisedSupport.setMethodInterceptor((MethodInterceptor) advisorAsp.getAdvice());
				advisedSupport.setMethodMatcher(advisorAsp.getPointcut().getMethodMatcher());

				//设置被代理对象
				TargetSource targetSource = new TargetSource(bean, bean.getClass().getInterfaces());
				advisedSupport.setTargetSource(targetSource);
				System.out.println("获取jdk 动态代理");
				return new JdkDynamicAopProxy(advisedSupport).getProxy();
			}
		}
		return bean;
	}

	public void setBeanFactory(BeanFactory beanFactory) throws Exception {
		this.beanFactory = (AbstractBeanFactory) beanFactory;
	}
}
