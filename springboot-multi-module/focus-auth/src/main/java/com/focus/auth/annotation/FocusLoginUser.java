package com.focus.auth.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 登录用户注解
 * 标注在Controller方法参数上，自动注入当前登录用户ID
 *
 * 用法：public FocusResult<Void> someApi(@FocusLoginUser Long uid)
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface FocusLoginUser {
}
