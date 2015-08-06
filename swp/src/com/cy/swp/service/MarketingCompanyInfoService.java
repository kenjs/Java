package com.cy.swp.service;

import com.cy.swp.domain.OrderCargoInfoDomain;

import java.util.List;

/**
 * Created by nixianjing on 14/12/11.
 */
public interface MarketingCompanyInfoService {


    /**
     * 根据导入获取信息获取企业客户信息
     * @param list
     * @autor nxj
     */
    public boolean addMarketingCompanyInfo(List<OrderCargoInfoDomain> list);
}
