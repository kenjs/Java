package com.cy.dcts.webUser.service.imp;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.webUser.dao.IWebUserInfoDao;
import com.cy.dcts.webUser.service.UpdatePwdService;
/**
 * 修改密码service
 * @date 2014-5-21
 * @author haoyong
 *
 */
public class UpdatePwdServiceImpl implements UpdatePwdService {
	private IWebUserInfoDao webUserInfoDao;

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public boolean updateUserPwd(WebUserInfo webUserInfo) {
		return webUserInfoDao.modifyWebUserInfoPasswordById(webUserInfo);
	} 
	
	public void setWebUserInfoDao(IWebUserInfoDao webUserInfoDao) {
		this.webUserInfoDao = webUserInfoDao;
	}
}
