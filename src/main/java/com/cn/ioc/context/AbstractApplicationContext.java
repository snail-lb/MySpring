package com.cn.ioc.context;

import java.util.List;

import com.cn.ioc.beans.BeanPostProcessor;
import com.cn.ioc.factory.AbstractBeanFactory;
import com.cn.ioc.factory.InitializingBean;

public abstract class AbstractApplicationContext implements ApplicationContext {

	protected AbstractBeanFactory beanFactory;

	public AbstractApplicationContext(AbstractBeanFactory abstractBeanFactory) {
		this.beanFactory = abstractBeanFactory;
	}

	public void refresh() throws Exception {
		loadBeanDefinitions(beanFactory);
		registerBeanPostProcessors(beanFactory);
		onRefresh();
	}
	
	protected void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws Exception {
		List<Object> beanPostProcessors = beanFactory.getBeansForType(BeanPostProcessor.class);
		for (Object beanPostProcessor : beanPostProcessors) {
			beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
		}
	}
	
	protected void onRefresh() throws Exception{
        beanFactory.preInstantiateSingletons();
    }

	public Object getBean(String name) throws Exception {
		Object bean = beanFactory.getBean(name);
		// 3.如果bean实现了ApplicationContextAwrae，则调用setApplicationContext方法
		if (bean instanceof ApplicationContextAwrae) {
			((ApplicationContextAwrae) bean).setApplicationContext(this);
		}
		
		//设置bean的生命周期
//		bean = beanFactory.setBeanLifecycle(bean,name);
		
		//bean的生命周期设置完毕，这里应该进行检查这个bean是否配置了AOP，
		//如果配置了，就将此类设置为被代理的类，进行代理，还有一点，这一步还不
		//知道spring实在bean生命周期的哪一步实现的，暂时先放在这里
		return bean;
	}
	
	protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;

}
