package com.cy.driver.service;

import com.cy.driver.bo.DriverUserInfoBo;
import com.cy.driver.domain.DriverUserInfoDomain;

import java.sql.SQLException;
import java.util.Map;

public interface LoginUserInfoService {

	/**
	 * 判断登陆用户名是否存在
	 * @param code
	 * @return true:存在,false：不存在
	 */
	public boolean checkUserAccountExist(String code);
	
	/**
	 * 登陆是否成功
	 * @param code
	 * @return true: 成功登陆,false：登陆失败
	 */
	public DriverUserInfoDomain checkLogin(String code) throws Exception;
	
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

	/**
	 * 注册
	 * @param bo
	 * @return
	 * @throws Exception
	 */
	public int register(DriverUserInfoBo bo, String checkCode) throws Exception;

	/**
	 * 更新司机APP版本
	 * @throws SQLException
	 */
	public void updateDriverAppVersion(DriverUserInfoBo bo) throws SQLException;
	
}
