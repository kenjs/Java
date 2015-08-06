package com.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestRun {

	public static void main(String[] args) {
		BeanFactory factory = new ClassPathXmlApplicationContext("spring-application-service.xml");  
		TestService aService = (TestService)factory.getBean("testService");   
        aService.cool("test....test");  
	}

}
