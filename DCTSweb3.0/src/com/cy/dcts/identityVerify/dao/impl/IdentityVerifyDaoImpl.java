package com.cy.dcts.identityVerify.dao.impl;

import java.util.List;
import java.util.Map;

import com.cy.dcts.common.bo.EffectiveIdentityInfo;
import com.cy.dcts.common.bo.IdentityVerifyLog;
import com.cy.dcts.common.bo.UserAccountInfo;
import com.cy.dcts.common.bo.UserConsumerRecord;
import com.cy.dcts.common.bo.UserRechargeInfo;
import com.cy.dcts.common.dao.BaseDao;
import com.cy.dcts.common.domain.EffectiveIdentityInfoDomain;
import com.cy.dcts.common.domain.IdentityVerifyLogDomain;
import com.cy.dcts.common.domain.UserAccountInfoDomain;
import com.cy.dcts.common.domain.UserConsumerRecordDomain;
import com.cy.dcts.common.domain.UserRechargeInfoDomain;
import com.cy.dcts.identityVerify.dao.IdentityVerifyDao;
/**
 * @description 身份验证有关数据库操作接口实现类
 * @author 		haoy
 *
 */
public class IdentityVerifyDaoImpl extends BaseDao implements IdentityVerifyDao {

	@Override
	public int insertIdentityVerifyLog(IdentityVerifyLog bo) throws Exception {
		return addObject("iBatisInsertIdentityVerifyLog", bo);
	}

	@Override
	public int insertEffectiveIdentityInfo(EffectiveIdentityInfo bo)
			throws Exception {
		return addObject("iBatisInsertEffectiveIdentityInfo", bo);
	}

	@Override
	public int insertUserAccountInfo(UserAccountInfo bo) throws Exception {
		return addObject("iBatisInsertUserAccountInfo", bo);
	}

	@Override
	public int insertUserConsumerRecord(UserConsumerRecord bo) throws Exception {
		return addObject("iBatisInsertUserConsumerRecord", bo);
	}

	@Override
	public int insertUserRechargeInfo(UserRechargeInfo bo) throws Exception {
		return addObject("iBatisInsertUserRechargeInfo", bo);
	}

	@Override
	public int updateUserAccountInfo(UserAccountInfo bo) throws Exception {
		return saveObject("iBatisUpdateUserAccountInfo", bo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IdentityVerifyLogDomain> selectIdentityVerifyLogList(
			Map<String, Object> map) throws Exception {
		return (List<IdentityVerifyLogDomain>) queryForList("iBatisSelectIdentityVerifyLog", map);
	}

	@Override
	public EffectiveIdentityInfoDomain selectEffectiveIdentityInfo(
			Map<String, Object> map) throws Exception {
		return (EffectiveIdentityInfoDomain) queryForObject("iBatisSelectEffectiveIdentityInfo", map);
	}

	@Override
	public UserAccountInfoDomain selectUserAccountInfo(String userId)
			throws Exception {
		return (UserAccountInfoDomain) queryForObject("iBatisSelectUserAccountInfo", userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserConsumerRecordDomain> selectUserConsumerRecord(String userId)
			throws Exception {
		return (List<UserConsumerRecordDomain>) queryForList("iBatisSelectUserConsumerRecord", userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRechargeInfoDomain> selectUserRechargeInfoList(String userId)
			throws Exception {
		return (List<UserRechargeInfoDomain>) queryForList("iBatisSelectUserRechargeInfo", userId);
	}

}
