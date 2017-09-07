package com.shiziqiu.configuration.console.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 判断是否需要登录
 * @title : Permession
 * @author : crazy
 * @date : 2017年9月6日 下午8:26:47
 * @Fun :
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Permession {

	boolean permession() default true;
}
