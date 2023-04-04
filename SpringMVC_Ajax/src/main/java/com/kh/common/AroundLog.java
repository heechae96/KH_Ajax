package com.kh.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class AroundLog {
	
	@Pointcut("execution(* com.kh..*Impl.*(..))")
	public void serviceImplPointCut() {
		
	}

	@Around("serviceImplPointCut()")
	public Object checkTimeMehod(ProceedingJoinPoint pjp) throws Throwable {
		
		// 스톱워치 역할을 하는 객체
		StopWatch sw = new StopWatch();
		Object obj = null;
		
		// 시작
		sw.start();
		
		// 메소드(대상)
		obj = pjp.proceed();	// ex) select, insert, update, delete, ... 메소드
		
		// 종료
		sw.stop();
		
		String methodName = pjp.getSignature().getName();
		
		System.out.println(methodName + " 메소드 수행에 걸린 시간 : " + sw.getTotalTimeMillis() + "(ms)");
		
		return obj;
	}
}
