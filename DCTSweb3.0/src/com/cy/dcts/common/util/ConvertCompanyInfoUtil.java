package com.cy.dcts.common.util;

import java.text.ParseException;

import com.cy.dcts.common.bo.CompanyInfo;
import com.cy.dcts.common.domain.WebUserInfoDamain;

public class ConvertCompanyInfoUtil {

	
	/**
	 * 对象类型转换
	 * @param webUserInfoDamain
	 * @return
	 * @throws ParseException
	 */
	public static CompanyInfo getWebUserInfoFromDomain(final WebUserInfoDamain webUserInfoDamain)throws ParseException {
		
		if(webUserInfoDamain == null) {
			return null;
		}
		CompanyInfo companyInfo = new CompanyInfo();
		
		return companyInfo;
	}
	
	/**
	 * 对象类型转换
	 * @param webUserInfoDamain
	 * @param companyInfo
	 * @throws ParseException
	 */
	public static void getWebUserInfoFromDomain(final WebUserInfoDamain webUserInfoDamain,final CompanyInfo companyInfo)throws ParseException {
		if(webUserInfoDamain == null) {
			return;
		}
		companyInfo.setId(webUserInfoDamain.getCompanyId());
		
	}

	
}
