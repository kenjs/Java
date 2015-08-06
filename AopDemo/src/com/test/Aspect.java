package com.test;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class Aspect {

	public Aspect() {
	}

	String strLog = null ;  
    
    public void doBefore(JoinPoint jp) {  
        strLog = "log Begining method: "  
                + jp.getTarget().getClass().getName() + "."  
                + jp.getSignature().getName(); 
        System.out.println("doBefore: " + strLog);
    }  
 
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {  
        long time = System.currentTimeMillis();  
        Object retVal = pjp.proceed();  
        time = System.currentTimeMillis() - time;  
        System.out.println("process time: " + time + " ms");  
        return retVal;  
    }  
   
    public void doAfter(JoinPoint jp) {  
        strLog ="doAfter:log Ending method: "  
                + jp.getTarget().getClass().getName() + "."  
                + jp.getSignature().getName();   
        System.out.println(strLog);
    }  
}
