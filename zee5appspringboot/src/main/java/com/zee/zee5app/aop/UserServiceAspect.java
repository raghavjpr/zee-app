package com.zee.zee5app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect // container where we will hold all our AOP code
public class UserServiceAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Pointcut("within(@org.springframework.stereotype.Repository *)"
			+ "|| within(@org.springframework.stereotype.Service *)"
			+ "|| within(@org.springframework.web.bind.annotation.RestController *)")
	public void springPointCutExp() {

	}

	@AfterThrowing(pointcut = "springPointCutExp()", throwing = "e")
	public void logAfterThrowingException(JoinPoint joinPoint, Throwable e) {
		log.error("exception {}.{}() with cause {} ", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");

	}

//	@Around(value = )

//	@Before(value = "execution(* com.zee.zee5app.service.impl.*.*(..))")
////	@Before(value = "execution(* com.zee.zee5app.service.impl.*.get*(..))")
////	if we write .get we will only have the before call when we have functions
////	with name stating with get called
//	public void beforeAllServiceMethods(JoinPoint joinPoint) {
//		System.out.println("Hello From Aspect Before!");
//		System.out.println(joinPoint);
//		System.out.println(joinPoint.getTarget());
//	}
//
//	@After(value = "execution(* com.zee.zee5app.service.impl.*.*(..))")
//	public void afterAllServiceMethods(JoinPoint joinPoint) {
//		System.out.println("Hello From Aspect After!");
//		System.out.println(joinPoint);
//		System.out.println(joinPoint.getTarget());
//	}

}
