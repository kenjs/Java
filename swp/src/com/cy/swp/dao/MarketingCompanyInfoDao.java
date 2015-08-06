package com.cy.swp.dao;

import com.cy.swp.bo.MarketingCompanyInfo;
import org.springframework.stereotype.Repository;


/**
 * Created by nixianjing on 14/12/11.
 */
@Repository("marketingCompanyInfoDao")
public interface MarketingCompanyInfoDao {


    /**
     * 根据手机号码查询企业客户条数
     * @param contactMobiphone
     * @autor nxj
     */
    public Integer queryMarketingCompanyInfoByContactMobiphone(String contactMobiphone);


    /**
     * 保存企业客户信息
     * @param marketingCompanyInfo
     * @autor nxj
     */
    public Integer addMarketingCompanyInfo(MarketingCompanyInfo marketingCompanyInfo);


}
