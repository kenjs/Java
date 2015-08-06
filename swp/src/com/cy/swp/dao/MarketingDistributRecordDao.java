package com.cy.swp.dao;

import com.cy.swp.bo.MarketingDistributRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 客户分配记录表数据库操作
 * Created by wyh on 2014/12/17.
 */
@Repository("marketingDistributRecordDao")
public interface MarketingDistributRecordDao {

    /**
     * 批量新增 分配时 分配给营销人员的记录 绑定
     * @param paraList
     * @return
     * @author wyh
     */
    int addBachApply(List<MarketingDistributRecord> paraList);

    /**
     * 批量新增 司机资料被转移时，记录被转移人的记录 解绑
     * @param paraList
     * @return
     * @author wyh
     */
    int addBachBeApply(List<MarketingDistributRecord> paraList);

    /**
     * 单条新增 绑定
     * @param recordPara
     * @return
     * @author wyh
     */
    int add(MarketingDistributRecord recordPara);

    /**
     * 批量新增 根据手机号码 营销人员导入时的记录 绑定
     * @param paraList
     * @return
     * @author wyh
     */
    int addBachApplyByMphone(List<Map<String, Object>> paraList);
}
