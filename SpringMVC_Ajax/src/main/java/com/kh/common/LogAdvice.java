package com.kh.common;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAdvice {
	
	@Pointcut("execution(* com.kh..*Impl.*(..))")
	public void serviceImplPointCut() {
		
	}

	@Before("serviceImplPointCut()")
	public void showLogBefore() {
		System.out.println("[공통로그 - LogAdvice] 비즈니스 로직 수행 전 동작!");
	}
	
	@After("serviceImplPointCut()")
	public void showLogAfter() {
		System.out.println("[공통로그 - LogAdvice] 비즈니스 로직 수행 후 동작!");
	}
	
}