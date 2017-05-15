package com.aopLog;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 自定义的注解，用于切入日志
 * @author Mark
 *
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
	// 模块名称
	String appModule();

	// 操作类型: Login, Add, Remove, Update
	String opType();

	// 操作类容
	String opText();
}
