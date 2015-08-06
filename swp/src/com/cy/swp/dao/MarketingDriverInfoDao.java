package com.cy.swp.dao;

import com.cy.swp.bo.MarketingDriverInfo;
import com.cy.swp.domain.MarketingDriverInfoDomain;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 司机客户资料表操作类
 * Created by wyh on 2014/12/9.
 */
@Repository("marketingDriverInfoDao")
public interface MarketingDriverInfoDao {

    /**
     * 根据手机号码查询
     * @autor wyh
     */
    MarketingDriverInfoDomain queryByPhone(Map<String, String> paraMap);

    /**
     * 批量新增
     * @param list
     * @autor wyh
     */
    int addBach(List<MarketingDriverInfoDomain> list);


    /**
     * 修改客户司机信息（常跑城市）
     * @param marketingDriverInfo
     * @autor nxj
     */
    public boolean updateMarketingDriverInfoSetOftenCity(MarketingDriverInfo marketingDriverInfo);


    /**
     * 修改客户司机信息（车牌号、车型、运力（重量、体积）姓名、删除状态）
     * @param marketingDriverInfo
     * @autor nxj
     */
    public boolean updateMarketingDriverInfoSetDriverInfo(MarketingDriverInfo marketingDriverInfo);

    /**
     * 修改客户司机信息（最近联系时间、下次联系时间）
     * @param marketingDriverInfo
     * @autor nxj
     */
    public boolean updateMarketingDriverInfoSetContactDate(MarketingDriverInfo marketingDriverInfo);


    /**
     * 修改客户司机信息（客户类别(1 2 3 4 5)）
     * @param marketingDriverInfo
     * @autor nxj
     */
    public boolean updateMarketingDriverInfoSetCategory(MarketingDriverInfo marketingDriverInfo);


    /**
     * 修改客户司机信息（手机号码2、手机号码3）
     * @param marketingDriverInfo
     * @return
     */
    public boolean updateMarketingDriverInfoSetMobilePhone(MarketingDriverInfo marketingDriverInfo);


    /**
     * 修改客户司机信息,手机号码无效（主手机号码标注无效的次数+1、营销人员Id清空、分配状态修改为待分配0、分配者id清空）
     * @param marketingDriverInfo
     * @return
     */
    public boolean updateMarketingDriverInfoSetMarkInvalidNums(MarketingDriverInfo marketingDriverInfo);


    /**
     * 根据id获取司机客户信息(已注册)
     * @param id
     * @return
     */
    public MarketingDriverInfoDomain queryMarketingDriverInfoDomainById(Integer id);


    /**
     * 根据id获取司机客户信息(未注册)
     * @param id
     * @return
     */
    public MarketingDriverInfoDomain queryMarketingDriverInfoDomainNoById(Integer id);


    /**
     * 单条新增
     * @param info
     * @return
     * @autor wyh
     */
    int add(MarketingDriverInfoDomain info);

    /**
     * 根据id、分配状态allocate_status单条修改
     * @param paraMap
     * @return
     * @autor wyh
     */
    int updateById(Map<String, Object> paraMap);

    /**
     * 根据id、分配状态allocate_status批量修改 同意或直接分配的修改
     * @param paraMap
     * @return
     * @autor wyh
     */
    int updateBachById(Map<String, Object> paraMap);

    /**
     * 根据id、分配状态allocate_status批量修改 审核环节直接分配的修改
     * @param paraMap
     * @return
     * @autor wyh
     */
    int updateBachByIdDirect(Map<String, Object> paraMap);

    /**
     * 根据id、分配状态allocate_status批量修改 不同意的修改
     * @param paraMap
     * @return
     * @autor wyh
     */
    int updateBachByIdDisagree(Map<String, Object> paraMap);

    /**
     * 客户分配的列表查询
     * @param paraMap
     * @return
     * @autor wyh
     */
    List<MarketingDriverInfoDomain> queryListPage(Map<String, Object> paraMap);

    /**
     * 未联系客户中所有客户的列表查询
     * @param paraMap
     * @return
     * @autor wyh
     */
    List<MarketingDriverInfoDomain> queryNoContAllListPage(Map<String, Object> paraMap);

    /**
     * 未联系客户中预约客户的列表查询
     * @param paraMap
     * @return
     * @autor wyh
     */
    List<MarketingDriverInfoDomain> queryNoContMakListPage(Map<String, Object> paraMap);

    /**
     * 营销人员分配申请的修改
     * @param paraMap
     * @return
     * @autor wyh
     */
    int updateBachApplyById(Map<String, Object> paraMap);

    /**
     * 根据id查询
     * @param id
     * @return
     * @autor wyh
     */
    MarketingDriverInfoDomain queryById(Integer id);

    /**
     * 根据id的in查询
     * @param paraMap
     * @return
     * @autor wyh
     */
    List<MarketingDriverInfoDomain> queryByIdIn(Map<String, Object> paraMap);

    /**
     * 未联系客户中所有客户条数查询
     * @param paraMap
     * @return
     * @autor wyh
     */
    int queryNoContAllListCount(Map<String, Object> paraMap);

    /**
     * 未联系客户中预约客户条数查询
     * @param paraMap
     * @return
     * @autor wyh
     */
    int queryNoContMakListCount(Map<String, Object> paraMap);

    /**
     * 客户分配条数查询
     * @param paraMap
     * @return
     * @autor wyh
     */
    int queryListCount(Map<String, Object> paraMap);


    /**
     * 根据driverId获取司机活跃信息(已注册)
     * @param driverId
     * @autor nxj
     */
    public MarketingDriverInfoDomain queryDriverInfoDomainByDriverId(Integer driverId);
}
