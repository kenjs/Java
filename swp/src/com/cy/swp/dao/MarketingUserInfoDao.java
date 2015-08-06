package com.cy.swp.dao;

import com.cy.swp.domain.MarketingUserInfoDomain;
import com.cy.swp.domain.StatisticalAnalyseDomain;
import org.springframework.stereotype.Repository;

import com.cy.swp.bo.MarketingUserInfo;

import java.util.List;
import java.util.Map;

@Repository("marketingUserInfoDao")
public interface MarketingUserInfoDao {
	
	public MarketingUserInfo queryMarketingUserInfo(MarketingUserInfo marketingUserInfo);

    /**
     * 根据职务position 查询
     * @param marketingUserInfo
     * @return
     * @author wyh
     */
    public List<MarketingUserInfo> queryByPosition(MarketingUserInfo marketingUserInfo);


    /**
     * 修改密码
     * @param marketingUserInfo
     * @return
     */
    public boolean updateUserPassword(MarketingUserInfo marketingUserInfo);


    public Integer queryMarketingUserInfoCount(Map<String,Object> map);

    public List<MarketingUserInfoDomain> queryMarketingUserInfoDomainPage(Map<String,Object> map);

    public List<MarketingUserInfoDomain> queryMarketingUserInfoDomainList(Map<String,Object> map);

    public Integer addMarketingUserInfo(MarketingUserInfoDomain marketingUserInfoDomain);

    public boolean updateMarketingUserInfo(MarketingUserInfoDomain marketingUserInfoDomain);

    /**
     * 统计分析查询
     * @ruthor wyh
     */
    public List<StatisticalAnalyseDomain> queryStatisticalAnalyse(Map<String,Object> map);
}
