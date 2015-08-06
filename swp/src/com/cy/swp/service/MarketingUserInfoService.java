package com.cy.swp.service;

import com.cy.swp.bo.MarketingUserInfo;
import com.cy.swp.domain.MarketingDriverInfoDomain;
import com.cy.swp.domain.MarketingUserInfoDomain;

import java.util.List;
import java.util.Map;

public interface MarketingUserInfoService {
	
	/**
	 * 用户登录
	 * @param marketingUserInfo
	 * @return
	 */
	public MarketingUserInfo MarketingUserInfoLogin(MarketingUserInfo marketingUserInfo);

    /**
     * 根据职务position获得人员（暂用于查询营销人员）
     * @param marketingUserInfo
     * @return
     */
    public List<MarketingUserInfo> queryByPositionSer(MarketingUserInfo marketingUserInfo);

    /**
     * 修改密码
     * @param marketingUserInfo
     * @return
     */
    public boolean updateUserPassword(MarketingUserInfo marketingUserInfo);


    public MarketingUserInfoDomain queryMarketingUserInfoDomainPagelist(MarketingUserInfoDomain marketingUserInfoDomain);


    public Integer addMarketingUserInfo(MarketingUserInfoDomain marketingUserInfoDomain);

    public boolean updateMarketingUserInfo(MarketingUserInfoDomain marketingUserInfoDomain);

    /**
     * 进入司机统计分析（查询）
     * @author wyh
     */
    public Map<String, Object> enterStatisticalAnalyseServ(MarketingDriverInfoDomain info);
}
