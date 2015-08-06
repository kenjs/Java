package com.cy.dctms.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cy.dctms.bo.OperationLogInfoBo;
import com.cy.dctms.logInterface.LogRecorder;
import com.cy.dctms.logInterface.impl.LogRecorderImpl;

@Aspect
public class LogJoinPoint {
	
	@Pointcut("@annotation(com.cy.common.annotation.LogAnnotation)")
	private void logAspect() {}
	
	@Before("logAspect()")
	public void doBefore(JoinPoint jp) {		
		System.out.println("begin ....");
	}
	
	@After("logAspect()")
	public void log2Db(JoinPoint jp) {
		BeanFactory factory = new ClassPathXmlApplicationContext("classpath*:spring-application-*.xml"); 
		LogRecorder logRecorder = (LogRecorderImpl) factory.getBean("logRecorder");
		
		OperationLogInfoBo log = new OperationLogInfoBo();
		log.setOperationType(100);
		log.setOperationName("test");
		log.setUserDriverId(7777);
		
		System.out.println(jp.getSignature().getName());
		
		System.out.println("add .........");
		
		logRecorder.addReqLogToQueue(log);
	}
	
	@AfterReturning("logAspect()")
	public void doAfterReturn() {
		System.out.println("doAfterReturn ....");
	}
}
