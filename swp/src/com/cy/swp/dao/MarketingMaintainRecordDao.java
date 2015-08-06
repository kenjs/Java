package com.cy.swp.dao;

import com.cy.swp.bo.MarketingMaintainRecord;
import com.cy.swp.domain.MarketingMaintainRecordDomain;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 客户维护记录Dao
 * Created by nixianjing on 14/12/10.
 */
@Repository("marketingMaintainRecordDao")
public interface MarketingMaintainRecordDao {


    /**
     * 查询客户维护记录，返回List对象（分页）
     * @param queryMap
     * @autor nxj
     */
    public List<MarketingMaintainRecordDomain> queryMarketingMaintainRecordDomainList(Map<String, Object> queryMap);


    /**
     * 获取总数量
     * @param queryMap
     * @return
     */
    public Integer queryMarketingMaintainRecordDomainCount(Map<String, Object> queryMap);



    /**
     * 保存客户维护记录
     * @param marketingMaintainRecord
     * @autor nxj
     */
    public Integer addMarketingMaintainRecord(MarketingMaintainRecord marketingMaintainRecord);

    /**
     * 根据客户种类、客户id记录内容查询客户维护记录，返回List对象
     * @param marketingMaintainRecord
     * @return
     * @author wyh
     */
    public List<MarketingMaintainRecordDomain> queryByRecordContentList(MarketingMaintainRecord marketingMaintainRecord);
}
