package com.cy.dcts.common.util;

import java.text.ParseException;
import java.util.Date;

import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.domain.WebUserInfoDamain;

public class ConvertWebUserInfoUtil {
	
	/**
	 * 对象类型转换
	 * @author nxj
	 * @param webUserInfoDamain
	 * @return
	 * @throws ParseException
	 */
	public static WebUserInfo getWebUserInfoFromDomain(final WebUserInfoDamain webUserInfoDamain)throws ParseException {
		
		if(webUserInfoDamain == null) {
			return null;
		}
		WebUserInfo webUserInfo = new WebUserInfo();
		webUserInfo.setId(webUserInfoDamain.getId());
		webUserInfo.setCode(webUserInfoDamain.getCode());
		webUserInfo.setPassword(webUserInfoDamain.getPassword());
		webUserInfo.setName(webUserInfoDamain.getName());
		webUserInfo.setCompanyId(webUserInfoDamain.getCompanyId());
		webUserInfo.setMobilephone(webUserInfoDamain.getMobilephone());
		webUserInfo.setUserQq(webUserInfoDamain.getUserQq());
		webUserInfo.setEmail(webUserInfoDamain.getEmail());
		webUserInfo.setIdCardNumber(webUserInfoDamain.getIdCardNumber());
		webUserInfo.setUserImages(webUserInfoDamain.getUserImages());
		webUserInfo.setDeletedFlag(webUserInfoDamain.getDeletedFlag());
		webUserInfo.setLoginIp(webUserInfoDamain.getLoginIp());
		webUserInfo.setEnterpriseFlag(webUserInfoDamain.getEnterpriseFlag());
		webUserInfo.setPanymentFlag(webUserInfoDamain.getPanymentFlag());
		webUserInfo.setPersonageFlag(webUserInfoDamain.getPersonageFlag());
		webUserInfo.setUserOrigin(webUserInfoDamain.getUserOrigin());
		webUserInfo.setUserType(webUserInfoDamain.getUserType());
		webUserInfo.setParentId(webUserInfoDamain.getParentId());
		webUserInfo.setEncoded(webUserInfoDamain.getEncoded());
		webUserInfo.setDeliveryFlag(webUserInfoDamain.getDeliveryFlag());
		webUserInfo.setArrivalSure(webUserInfoDamain.getArrivalSure());
		webUserInfo.setReceiveSure(webUserInfoDamain.getReceiveSure());
		return webUserInfo;
	}
	
	/**
	 * 对象类型转换
	 * @author nxj
	 * @param webUserInfoDamain
	 * @param webUserInfo
	 * @throws ParseException
	 */
	public static void getWebUserInfoFromDomain(final WebUserInfoDamain webUserInfoDamain,final WebUserInfo webUserInfo)throws ParseException {
		if(webUserInfoDamain == null) {
			return;
		}
		webUserInfo.setId(webUserInfoDamain.getId());
		webUserInfo.setId(webUserInfoDamain.getId());
		webUserInfo.setCode(webUserInfoDamain.getCode());
		webUserInfo.setPassword(webUserInfoDamain.getPassword());
		webUserInfo.setName(webUserInfoDamain.getName());
		webUserInfo.setCompanyId(webUserInfoDamain.getCompanyId());
		webUserInfo.setMobilephone(webUserInfoDamain.getMobilephone());
		webUserInfo.setUserQq(webUserInfoDamain.getUserQq());
		webUserInfo.setEmail(webUserInfoDamain.getEmail());
		webUserInfo.setIdCardNumber(webUserInfoDamain.getIdCardNumber());
		webUserInfo.setUserImages(webUserInfoDamain.getUserImages());
		webUserInfo.setDeletedFlag(webUserInfoDamain.getDeletedFlag());
		webUserInfo.setLoginIp(webUserInfoDamain.getLoginIp());
		webUserInfo.setEnterpriseFlag(webUserInfoDamain.getEnterpriseFlag());
		webUserInfo.setPanymentFlag(webUserInfoDamain.getPanymentFlag());
		webUserInfo.setPersonageFlag(webUserInfoDamain.getPersonageFlag());
		webUserInfo.setUserOrigin(webUserInfoDamain.getUserOrigin());
		webUserInfo.setUserType(webUserInfoDamain.getUserType());
		webUserInfo.setParentId(webUserInfoDamain.getParentId());
		webUserInfo.setEncoded(webUserInfoDamain.getEncoded());
		webUserInfo.setDeliveryFlag(webUserInfoDamain.getDeliveryFlag());
		webUserInfo.setArrivalSure(webUserInfoDamain.getArrivalSure());
		webUserInfo.setReceiveSure(webUserInfoDamain.getReceiveSure());
	}

}
