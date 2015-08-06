package com.cy.dcts.webUser.service;

import java.util.List;
import java.util.Map;

import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.domain.WebUserInfoDamain;

public interface IQueryWebUserInfoService {
	/**
	 * 根据用户Id查询用户及公司信息
	 * @param id
	 * @return
	 */
	public WebUserInfoDamain queryWebUserCompanyoyById(String id);
	
	/**
	 * 根据父级Id，用户类型和编码查询用户
	 * @param parentId 父级Id
	 * @param encoded 编码
	 * @param userType 用户类型
	 * @return
	 */
	public Integer queryWebUserByParentIdUsertypeEncoded(String parentId,String encoded,String userType);
	
	/**
	 * 查询用户信息
	 * 
	 * 查询条件可以多个
	 * 不需要的为空
	 * @author nxj
	 * @param webUserInfo
	 * @return
	 */
	public List<WebUserInfo> queryWebUserInfo(WebUserInfo webUserInfo);
	
	
	/**
	 * 根据用户id查询用户信息
	 * @author nxj
	 * @param userId
	 * @return
	 */
	public WebUserInfo queryWebUserInfoById(String userId);
	
	/**
	 * 根据用户code查询用户信息
	 * @author nxj
	 * @param code
	 * @return
	 */
	public WebUserInfo queryWebUserInfoByCode(String code);
	
	/**
	 * 根据用户mobilephone查询用户信息
	 * @author nxj
	 * @param mobilephone
	 * @return
	 */
	public WebUserInfo queryWebUserInfoByMobilephone(String mobilephone);
	
	/**
	 * 修改用户标识
	 * @param driverId
	 */
	public void updateUserFlag(Map<String,Object> map);
	
	
	/**
	 * 查询子账号分页查询
	 * @author nxj
	 * @param WebUserInfoDamain
	 * @return
	 */
	public List<WebUserInfoDamain> querySonWebUserInfoPage(WebUserInfoDamain webUserInfoDamain);
	
	
	/**
	 * 查询子账号分页查询
	 * @author nxj
	 * @param WebUserInfoDamain
	 * @return
	 */
	public List<WebUserInfoDamain> querySonWebUserInfoById(WebUserInfoDamain webUserInfoDamain);
	
	/**
	 * 获取下一个编码代码
	 * @return
	 * @throws Exception
	 */
	public String getNextEncode() throws Exception ;
}