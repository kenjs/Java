package com.cy.swp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cy.swp.bo.MarketingCargoDriverContacts;

@Repository("marketingCargoDriverContactsDao")
public interface MarketingCargoDriverContactsDao {
	
	/**
	 * 根据driver_user_id查询司机回复记录
	 * @param driverUserId 司机Id
	 * @return
	 */
	List<MarketingCargoDriverContacts> queryDriverReplyByDriverId(String driverUserId);
	
	/**
	 * 查询某条货源是否有司机有意向去拉这个货（判断该条货源是否匹配成功）
	 * @return zdy
	 */
	int countDriverReplyByAssistIdAndResult(Map<String, Object> queryMap);
	
	/**
	 * 添加货源司机电话回访记录
	 * @param marketingCargoDriverContacts
	 * @return
	 */
	int addCargoDriverContacts(MarketingCargoDriverContacts marketingCargoDriverContacts);
}
