package com.cy.driver.dao;

import com.cy.driver.bo.DriverUserInfoBo;
import com.cy.driver.domain.CompanyInfoDomain;
import com.cy.driver.domain.DriverBusinessLineInfoDomain;
import com.cy.driver.domain.DriverCallDomain;
import com.cy.driver.domain.DriverUserInfoDomain;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
/**
 * 货源信息dao
 * @date 2014-5-29
 * @author haoyong
 *
 */
@Repository("driverUserCargoInfoDao")
public interface DriverUserCargoInfoDao {

	/**
	 * 查找司机预约线路
	 * @param driverId
	 * @return
	 */
	public List<DriverBusinessLineInfoDomain> selectDriverBusinessLineInfoByDriverId(String driverId);
	
	/**
	 * 符合预约的货物数量
	 * @param map
	 * @return
	 */
	public int selectSuitCargoCount(Map<String,Object> map);
	
	/**
	 * 附近的货源数量
	 * @param map
	 * @return
	 */
	public int selectNearByCargoCount(Map<String,Object> map);
	
	/**
	 * 根据登录手机号修改司机信息
	 * @param bo
	 * @return
	 */
	public int updateDriverUserInfo(DriverUserInfoBo bo);
	
	/**
	 * 查询司机最新地址
	 * @param driverId
	 * @return
	 */
	public Map<String,Object> selectDriverLastLocation(String driverId );
	
	/**
	 * 查询用户基本信息
	 * @param driverId
	 * @return
	 */
	public DriverUserInfoDomain selectUserBasicInfo(String driverId);
	
	/**
	 * 司机个人消息查询
	 * @param map
	 * @return
	 */
	public List<?> queryDriverNotificationInfo(Map<String,Object> map);
	
	/**
	 * 查询公司信息
	 * @param id
	 * @return
	 */
	public CompanyInfoDomain selectConpanyInfoById(String id);

    /**
     * 司机登陆名是否存在
     * @param code
     * @return 1：存在，0:不存在
     */
    public int checkUserAccountExist(String code);

    /**
     * 是否登陆成功
     * @param code
     * @return
     */
    public DriverUserInfoDomain checkLogin(String code);

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
    public Object checkUser(String id);

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

    public DriverCallDomain selectDriverIsCall(String driverId) throws SQLException;

    public DriverCallDomain selectDriverDayilyIsCall(String driverId) throws SQLException;

    public void updateCountDriverUserBusi(Map<String,Object> map) throws SQLException;

    public void updateDayCountDriverUserBusi(Map<String,Object> map) throws SQLException;

    public void insertCountDriverUserBusi(Map<String,Object> map) throws SQLException;

    public void insertDayCountDriverUserBusi(Map<String,Object> map) throws SQLException;

    /**
     * 首页待确认的VIP会员数量
     * @param driverId
     * @return
     * @throws SQLException
     */
    public int queryPactVipDriverNum(String driverId) throws SQLException;

    /**
     * 查询首页回单、发货单数量
     * @return
     * @throws SQLException
     */
    public int queryHomeReceiptNum(String driverId) throws SQLException;

    /**
     * 首页我的货单等待确认数量
     * @return
     * @throws SQLException
     */
    public int queryHomePageUnconfirmedReceiptNum(String driverId) throws SQLException;
}
