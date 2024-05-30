package com.shinhan.myapp.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// ë³´ì¡°?—…ë¬?(cross cutting concern)
@Component
//@Aspect	// º¸Á¶¾÷¹« + Poincut
public class LoggingAdvice {
	
	@Pointcut("execution(* selectAll())")
	public void targetMethod2() {}
	
	@Pointcut("within(com.shinhan.myapp.model.BoardService)")
	public void targetMethod3() {}
	
	@Before("targetMethod3()")
	public void f_before() {
		System.out.println("----- @Before -----");
	}
	
	@After("targetMethod3()")
	public void f_after() {
		System.out.println("----- @After -----");
	}
	
	@AfterReturning("targetMethod3()")
	public void f_afterReturn() {
		System.out.println("----- @AfterReturning -----");
	}
	
	// target
	@Around("targetMethod2()")
	public Object aroundMethod(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("LogginAdvice around");
		System.out.println(jp.getSignature().getName());

		// 
		Object object = jp.proceed();

		// 
		System.out.println("loggingAdvice around");
		System.out.println(jp.getSignature().getName());
		return object;
	}

}
