/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.laidians.utils;

import java.lang.annotation.Annotation;

/**
 * 判断类或者接口是否拥有注解属性
 * 摘自 org.quartz.utils
 * @author wxylon@gmail.com
 * @date 2012-9-6
 */
public class ClassUtils {
	
	/**
	 * 检查类是否拥有指定注解<br/>
	 * eg.任一父类拥有指定注解 返回true;
	 * eg.任一接口拥有指定注解 返回true;
	 * eg.其他 false;
	 * @param clazz		指定类
	 * @param a			指定注解
	 * @return			true/false
	 * @author wxylon@gmail.com
	 * @date 2012-9-6
	 */
	public static boolean isAnnotationPresent(Class<?> clazz, Class<? extends Annotation> a) {

		if (clazz == null)
			return false;

		if (clazz.isAnnotationPresent(a))
			return true;

		if (isAnnotationPresentOnSuperClasses(clazz, a))
			return true;

		if (isAnnotationPresentOnInterfaces(clazz, a))
			return true;

		return false;
	}

	private static boolean isAnnotationPresentOnSuperClasses(Class<?> clazz, Class<? extends Annotation> a) {

		if (clazz == null)
			return false;

		Class<?> c = clazz.getSuperclass();
		while (c != null && !c.equals(Object.class)) {
			if (c.isAnnotationPresent(a))
				return true;
			if (isAnnotationPresentOnInterfaces(c, a))
				return true;
			c = c.getSuperclass();
		}

		if (isAnnotationPresentOnInterfaces(clazz.getSuperclass(), a))
			return true;

		return false;
	}

	private static boolean isAnnotationPresentOnInterfaces(Class<?> clazz, Class<? extends Annotation> a) {

		if (clazz == null)
			return false;

		for (Class<?> i : clazz.getInterfaces()) {
			if (i.isAnnotationPresent(a))
				return true;
			if (isAnnotationPresentOnInterfaces(i, a))
				return true;
		}

		return false;
	}
}
