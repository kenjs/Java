package com.cy.dcts.identityVerify.dao;

import java.util.List;
import java.util.Map;

import com.cy.dcts.common.bo.EffectiveIdentityInfo;
import com.cy.dcts.common.bo.IdentityVerifyLog;
import com.cy.dcts.common.bo.UserAccountInfo;
import com.cy.dcts.common.bo.UserConsumerRecord;
import com.cy.dcts.common.bo.UserRechargeInfo;
import com.cy.dcts.common.domain.EffectiveIdentityInfoDomain;
import com.cy.dcts.common.domain.IdentityVerifyLogDomain;
import com.cy.dcts.common.domain.UserAccountInfoDomain;
import com.cy.dcts.common.domain.UserConsumerRecordDomain;
import com.cy.dcts.common.domain.UserRechargeInfoDomain;
/**
 * @description 身份验证有关数据库操作接口
 * @author 		haoy
 *
 */
public interface IdentityVerifyDao {

	/**
	 * 新增身份验证日志
	 * @param bo
	 * @return key
	 * @throws Exception
	 */
	public int insertIdentityVerifyLog(IdentityVerifyLog bo) throws Exception;
	
	/**
	 * 新增身份信息合法库
	 * @param bo
	 * @return key
	 * @throws Exception
	 */
	public int insertEffectiveIdentityInfo(EffectiveIdentityInfo bo) throws Exception;
	
	/**
	 * 新增用户账户
	 * @param bo
	 * @return key
	 * @throws Exception
	 */
	public int insertUserAccountInfo(UserAccountInfo bo) throws Exception;
	
	/**
	 * 新增用户消费记录
	 * @param bo
	 * @return key
	 * @throws Exception
	 */
	public int insertUserConsumerRecord(UserConsumerRecord bo) throws Exception;
	
	/**
	 * 新增用户充值记录
	 * @param bo
	 * @return key 
	 * @throws Exception
	 */
	public int insertUserRechargeInfo(UserRechargeInfo bo) throws Exception;
	
	/**
	 * 修改用户账户信息
	 * @param bo
	 * @return 1 or 0
	 * @throws Exception
	 */
	public int updateUserAccountInfo(UserAccountInfo bo) throws Exception;
	
	/**
	 * 身份验证日志查询
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	public List<IdentityVerifyLogDomain> selectIdentityVerifyLogList(Map<String, Object> map) throws Exception;
	
	/**
	 * 根据身份证号码和姓名查询验证信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public EffectiveIdentityInfoDomain selectEffectiveIdentityInfo(Map<String, Object> map) throws Exception;
	
	/**
	 * 查询用户账户信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public UserAccountInfoDomain selectUserAccountInfo(String userId) throws Exception;
	
	/**
	 * 查询用户消费记录
	 * @param userId
	 * @return list
	 * @throws Exception
	 */
	public List<UserConsumerRecordDomain> selectUserConsumerRecord(String userId) throws Exception;
	
	/**
	 * 查询用户充值记录
	 * @param userId
	 * @return list
	 * @throws Exception
	 */
	public List<UserRechargeInfoDomain> selectUserRechargeInfoList(String userId) throws Exception;
}
