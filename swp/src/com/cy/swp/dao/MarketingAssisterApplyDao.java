package com.cy.swp.dao;

import com.cy.swp.bo.MarketingAssisterApply;
import com.cy.swp.domain.MarketingAssisterApplyDomain;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 客户分配申请表数据库操作类
 * Created by wyh on 2014/12/10.
 */
@Repository("marketingAssisterApplyDao")
public interface MarketingAssisterApplyDao {

    /**
     * 根据审核状态audit_status查询客户分配申请数据集合
     * @param paraMap
     * @author wyh
     */
    List<MarketingAssisterApplyDomain> queryByAuditStatus(Map<String, Object> paraMap);

    /**
     * 根据id单条修改审核状态audit_status为0 客户分配申请数据
     * @param paraMap
     * @author wyh
     */
    int updateById(Map<String, Object> paraMap);

    /**
     * 根据id批量修改审核状态audit_status为0 id in ()
     * @param paraMap
     * @author wyh
     */
    int updateBachById(Map<String, Object> paraMap);

    /**
     * 根据id批量修改审核状态audit_status为0 for update 直接分配
     * @param paraMap
     * @return
     * @author wyh
     */
    int updateForBachById(Map<String, Object> paraMap);

    /**
     * 批量新增客户分配申请数据
     * @param paraList
     * @author wyh
     */
    int addBach(List<MarketingAssisterApply> paraList);

    /**
     * 单条新增客户分配申请数据
     * @param assisterApply
     * @author wyh
     */
    int add(MarketingAssisterApply assisterApply);
}
