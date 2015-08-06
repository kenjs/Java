package com.cy.driver.user.dao;

import java.util.Map;

import com.cy.common.bo.DriverUserInfoBo;
import com.cy.driver.user.domain.DriverUserInfoDomain;

public interface LoginUserInfoDao {

	/**
	 * 司机登陆名是否存在
	 * @param userAccount
	 * @return 1：存在，0:不存在
	 */
	public int checkUserAccountExist(String code);
	
	/**
	 * 是否登陆成功
	 * @param userAccount
	 * @param pwd
	 * @return
	 */
	public DriverUserInfoDomain checkLogin(String code,String pwd);
	
	/**
	 * 注册用户
	 * @param bo
	 * @return
	 */
	public int addDriverUserInfo(DriverUserInfoBo bo);
	
	/**
	 * 判断用户有效性
	 * @param id
	 * @return
	 */
	public int checkUser(String id);
	
	/**
	 * 百度云推送id
	 * @param map
	 * @return
	 */
	public int updateBaiduPushId(Map<String,Object> map);
	
	/**
	 * 验证账户是否被冻结
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public int chkFreezeAccount(String code) throws Exception;
}
