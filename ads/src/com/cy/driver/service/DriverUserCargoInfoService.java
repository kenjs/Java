package com.cy.driver.service;

import com.cy.driver.bo.DriverUserInfoBo;
import com.cy.driver.bo.JSonResponse;
import com.cy.driver.domain.CompanyInfoDomain;
import com.cy.driver.domain.DriverUserInfoDomain;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * 货源信息service
 * @date 2014-5-29
 * @author haoyong
 *
 */
public interface DriverUserCargoInfoService {
	
	/**
	 * 根据登录手机号修改Driver信息
	 * @param bo
	 * @return
	 * @throws java.sql.SQLException
	 */
	public int updateDriverUserInfo(DriverUserInfoBo bo) throws SQLException;
	
	/**
	 * 查询用户基本信息
	 * @param driverId
	 * @return
	 * @throws java.sql.SQLException
	 */
	public DriverUserInfoDomain selectUserBasicInfo(String driverId) throws Exception;
	
	
	/**
	 * 司机个人消息查询
	 * @param driverId
	 * @return
	 */
	public List<?> queryDriverNotificationInfo(String driverId,String fromSize,String listSize);
	
	/**
	 * 短信发送
     * @param requestIp
	 * @param phoneNumber
	 * @param content
     * @param captcha
     * @param purpose
	 * @return
	 */
	public int noteSend(String requestIp, String phoneNumber,String content, String captcha, int purpose) throws Exception;
	
	/**
	 * 查询公司信息
	 * @param id
	 * @return
	 */
	public CompanyInfoDomain selectConpanyInfoById(String id);
	
	/**
	 * 货源提醒
	 * @param map
	 * @return
	 */
	public String cargoInfoRemind(Map<String,String> map);

    /**
     * 统计司机拨打电话
     * @param driverId
     * @param type
     * @return
     * @throws Exception
     */
    public int driverCall(String driverId, String type) throws Exception;

    /**
     * 初始化首页显示数目
     * @param driverId
     * @return
     * @throws SQLException
     */
    public String initHomePageNum(String driverId) throws SQLException;

    /**
     *
     * @param driverId
     * @return
     * @throws SQLException
     */
    public int queryPactVipDriverNum(String driverId) throws SQLException;

    public int selectSuitCargoCount(String driverId) throws SQLException;

    public int selectNearByCargoCount(String driverId) throws SQLException;
    
    /**
     * 版本更新检查
     * @param driverId  司机id，可为空
     * @param currentVersion 当前版本号
     * @param innerVersion	平台更新次数
     * @return
     * @throws SQLException
     */
    public Object checkVersion(String driverId, String currentVersion, int type, String innerVersion) throws Exception;

	/**
	 * 提交认证
	 * @param driverId
	 * @param identityLicenseNum
	 * @return
	 * @throws SQLException
	 */
	public JSonResponse submitCertification(String driverId, String identityLicenseNum) throws SQLException;
}
