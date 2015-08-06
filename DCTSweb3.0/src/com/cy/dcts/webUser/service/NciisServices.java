package com.cy.dcts.webUser.service;
/**
 * 身份验证接口 
 * @author Administrator
 *
 */
public interface NciisServices {

	//检查方法
	public String nciicCheck(String inLicense,String inConditions);
	
	//取得条件文件模板
	public String nciicGetCondition(String inLicense) throws Exception;
}
