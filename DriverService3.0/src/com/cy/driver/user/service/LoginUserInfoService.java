package com.cy.driver.user.service;

import java.util.Map;

import com.cy.common.bo.DriverUserInfoBo;
import com.cy.driver.user.domain.DriverUserInfoDomain;

public interface LoginUserInfoService {

	/**
	 * 判断登陆用户名是否存在
	 * @param userAccount
	 * @return true:存在,false：不存在
	 */
	public boolean checkUserAccountExist(String code);
	
	/**
	 * 登陆是否成功
	 * @param userAccount
	 * @param pwd
	 * @return true: 成功登陆,false：登陆失败
	 */
	public DriverUserInfoDomain checkLogin(String code,String pwd);
	
	/**
	 * 注册用户
	 * @param code
	 * @return
	 */
	public int addDriverUserInfo(DriverUserInfoBo bo);
	
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
	public boolean chkFreezeAccount(String code) throws Exception;
	
}
