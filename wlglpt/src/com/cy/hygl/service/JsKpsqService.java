package com.cy.hygl.service;

import com.cy.common.service.BaseBusinessService;
import com.cy.hygl.domain.JsKpsqDomain;

/**
 * The SERVICE for ¿ªÆ±ÉêÇë.
 * 
 * @author HJH
 */

public interface JsKpsqService extends BaseBusinessService {
	
	public void querySrKpMx(JsKpsqDomain domain) throws Exception;
	
	public void savaSrKpMxTemp(JsKpsqDomain domain) throws Exception;
	
	public void deleteSqKpTemp(JsKpsqDomain domain) throws Exception;
	
	public void deleteSrKpMx(JsKpsqDomain domain) throws Exception;
	
}