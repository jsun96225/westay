/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.westay.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring Context 工具类
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
	public static ApplicationContext applicationContext; 

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextUtils.applicationContext = applicationContext;
	}

	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	public static <T> T getBean(Class<T> requiredType) {
		return applicationContext.getBean(requiredType);
	}

	public static <T> T getBean(String name, Class<T> requiredType) {
		return applicationContext.getBean(name, requiredType);
	}

	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	public static boolean isSingleton(String name) {
		return applicationContext.isSingleton(name);
	}

	public static Class<? extends Object> getType(String name) {
		return applicationContext.getType(name);
	}

}