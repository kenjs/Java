package com.cy.dcts.webUser.service;

import com.cy.dcts.common.bo.WebUserInfo;

public interface ISaveWebUserInfoService {
	
	/**
	 * 注册WEB用户
	 * @author nxj
	 * @param webUserInfo
	 * @return
	 */
	public String addWebUserInfo(WebUserInfo webUserInfo);
	
	
	/**
	 * 维护WEB子级用户
	 * @author nxj
	 * @param webUserInfo
	 * @return
	 */
	public String addSonWebUserInfo(WebUserInfo webUserInfo);
	
	/**
	 * 完善用户信息
	 * @author nxj
	 * @param webUserInfo
	 * @return
	 */
	public boolean modifyWebUserInfoById(WebUserInfo webUserInfo);
	
	
	/**
	 * 个人认证
	 * @author nxj
	 * @param webUserInfo
	 * @return
	 */
	public boolean modifyWebUserInfoPersonageFlagById(WebUserInfo webUserInfo);
	
	
	/**
	 * 企业认证
	 * @author nxj
	 * @param webUserInfo
	 * @return
	 */
	public boolean modifyWebUserInfoEnterpriseFlagById(WebUserInfo webUserInfo);
	
	
	/**
	 * 缴费认证 
	 * @author nxj
	 * @param webUserInfo
	 * @return
	 */
	public boolean modifyWebUserInfoPanymentFlagById(WebUserInfo webUserInfo);
	
	
	/**
	 * 修改登陆密码
	 * @author nxj
	 * @param webUserInfo
	 * @return
	 */
	public boolean modifyWebUserInfoPasswordById(WebUserInfo webUserInfo);
	
	/**
	 * 修改手机号码
	 * haoyong
	 * @param webUserInfo
	 * @return
	 */
	public boolean modifyWebUserInfoMobilephoneById(WebUserInfo webUserInfo);
	
	
	/**
	 * 老用户获取编码代码（六位）
	 * @author nxj
	 * @param webUserInfo
	 * @return
	 */
	public boolean modifyWebUserInfoEncodedById(WebUserInfo webUserInfo);
	
	
	/**
	 * 修改子账号
	 * 
	 * @author nxj
	 * @param webUserInfo
	 * @return
	 */
	public boolean modifySonWebUserById(WebUserInfo webUserInfo);

}
