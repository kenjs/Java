package com.cy.swp.service;

import com.cy.swp.bo.MarketingUserInfo;
import com.cy.swp.domain.MarketingDriverInfoDomain;
import com.cy.swp.domain.MarketingMaintainRecordDomain;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 客户分配申请Service
 * Created by wyh on 2014/12/15.
 */
public interface MarketingAssisterApplyService {

    /**
     * 分配申请service
     * @param info
     * @param user
     * @return
     * @author wyh
     */
    public Map<String, String> applyDriverServ(MarketingDriverInfoDomain info, MarketingUserInfo user)throws SQLException;

    /**
     * 刷新营销人员导入后展示的待分配司机service
     * @param driverIds
     * @return
     * @author wyh
     */
    public List<MarketingDriverInfoDomain> refreshImportDriverServ(String[] driverIds);

    /**
     * //添加单条司机客户
     * @param info
     * @param user
     * @return
     * @author wyh
     */
    public Map<String, Object> addSingleDriverServ(MarketingDriverInfoDomain info, MarketingUserInfo user);

    /**
     * 申请单条司机客户serv
     * @param info
     * @param user
     * @return
     * @author wyh
     */
    public Map<String, Object> applySingleDriverServ(MarketingDriverInfoDomain info, MarketingUserInfo user);

    /**
     * 查询标记司机手机号码无效的记录serv
     * @param id
     * @param customerKind
     * @return
     * @author wyh
     */
    public List<MarketingMaintainRecordDomain> queryMaintainRecordServ(String id,Integer customerKind);
}
