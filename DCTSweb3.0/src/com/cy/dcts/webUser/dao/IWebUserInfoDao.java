package com.cy.dcts.webUser.dao;

import java.util.List;
import java.util.Map;

import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.domain.WebUserInfoDamain;

public interface IWebUserInfoDao {
	/**
	 * 根据用户Id查询用户及公司信息
	 * @param id
	 * @return
	 */
	public WebUserInfoDamain queryWebUserCompanyoyById(String id);
	
	
	/**
	 * 根据父级Id，用户类型和编码查询用户
	 * @param webUserInfo 
	 * @return
	 */
	public Integer queryWebUserByParentIdUsertypeEncoded(Map <String, Object> queryMap);
	
	/**
	 * 查询用户信息
	 * 
	 * 查询条件可以多个
	 * 不需要的为空
	 * @author nxj
	 * @param queryMap
	 * @return
	 */
	public List<WebUserInfo> queryWebUserInfo(Map <String, Object> queryMap);
	
	
	/**
	 * 查询子账号分页查询
	 * @author nxj
	 * @param WebUserInfoDamain
	 * @return
	 */
	public List<WebUserInfoDamain> querySonWebUserInfoPage(Map <String, Object> queryMap);
	
	
	/**
	 * 查询子账号分页查询(总条数)
	 * @author nxj
	 * @param WebUserInfoDamain
	 * @return
	 */
	public Integer querySonWebUserInfoPageCount(Map <String, Object> queryMap);
	
	
	/**
	 * 查询 所有子账号
	 * @author nxj
	 * @param WebUserInfoDamain
	 * @return
	 */
	public List<WebUserInfoDamain> querySonWebUserInfoList(Map <String, Object> queryMap);
	
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
	 * 获取父级最大编码
	 * @author nxj
	 * @return
	 */
	public WebUserInfo queryWebUserInfoEncoded();
	
	
	/**
	 * 获取父级维护子级最大编码
	 * @author nxj
	 * @return
	 */
	public WebUserInfo queryWebUserInfoEncodedByParentId(Map <String, Object> queryMap);
	
	
	/**
	 * 注册WEB用户
	 * @author nxj
	 * @param webUserInfo
	 * @return
	 */
	public String addWebUserInfo(WebUserInfo webUserInfo);
	
	
	/**
	 * 完善用户信息
	 * @author nxj
	 * @param webUserInfo
	 * @return
	 */
	public int modifyWebUserInfoById(WebUserInfo webUserInfo);
	
	
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
	 * 修改认证手机号码 
	 * @author nxj
	 * @param webUserInfo
	 * @return
	 */
	public boolean modifyWebUserInfoMobilephoneById(WebUserInfo webUserInfo);
	
	/**
	 * 修改用户标识
	 * @param driverId
	 */
	public void updateUserFlag(Map<String,Object> map);
	
	
	/**
	 * 老用户获取编码代码（六位）
	 * @author nxj
	 * @param webUserInfo
	 * @return
	 */
	public boolean modifyWebUserInfoEncodedById(WebUserInfo webUserInfo);
	
	
	/**
	 * 修改子账号
	 * @param webUserInfo
	 * @return
	 */
	public boolean modifySonWebUserInfoById(WebUserInfo webUserInfo);
	
	
	
}
