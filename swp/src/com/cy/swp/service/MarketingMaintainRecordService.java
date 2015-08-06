package com.cy.swp.service;

import com.cy.swp.bo.MarketingMaintainRecord;
import com.cy.swp.domain.MarketingMaintainRecordDomain;

import java.util.List;
import java.util.Map;

/**
 * 客户维护记录Service
 * Created by nixianjing on 14/12/10.
 */
public interface MarketingMaintainRecordService {


    /**
     * 保存客户维护记录
     * @param marketingMaintainRecord
     * @autor nxj
     */
    public Integer addMarketingMaintainRecord(MarketingMaintainRecord marketingMaintainRecord);


    /**
     * 查询客户维护记录，返回List对象（分页）
     * @param marketingMaintainRecordDomain
     * @autor nxj
     */
    public MarketingMaintainRecordDomain queryMarketingMaintainRecordDomainList(MarketingMaintainRecordDomain marketingMaintainRecordDomain);


    /**
     * 封装数据
     * @param list
     * @return
     */
    public String getMarketingMaintainRecordHtml(List<MarketingMaintainRecordDomain> list,Integer curPage,Integer pageSize);

}
