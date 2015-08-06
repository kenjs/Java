package com.cy.swp.dao;

import com.cy.swp.bo.DriverBusinessLineInfo;
import com.cy.swp.bo.MarketingDriverBusinessLine;
import com.cy.swp.domain.MarketingDriverBusinessLineDomain;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 客户司机预约线路表Dao
 * Created by nixianjing on 14/12/9.
 */
@Repository("marketingDriverBusinessLineDao")
public interface MarketingDriverBusinessLineDao {


    /**
     * 根据客户id获取客户司机预约线路
     * @param queryMap
     * @autor nxj
     */
    public List<MarketingDriverBusinessLineDomain> queryMarketingDriverBusinessLineByCustomerDriverId(Map<String, Object> queryMap);


    /**
     * 根据司机id获取司机预约线路
     * @param queryMap
     * @autor nxj
     */
    public List<MarketingDriverBusinessLineDomain> queryDriverBusinessLineInfoByDriverId(Map<String, Object> queryMap);


    /**
     * 根据预约线路id查询预约线路对象
     * @param id
     * @author nxj
     */
    public MarketingDriverBusinessLineDomain queryMarketingDriverBusinessLineById(Integer id);


    /**
     * 根据预约线路id查询预约线路对象
     * @param id
     * @author nxj
     */
    public MarketingDriverBusinessLineDomain queryDriverBusinessLineInfoById(Integer id);


    /**
     * 根据id修改客户司机预约线路信息
     * @param marketingDriverBusinessLine
     * @autor nxj
     */
    public boolean updateMarketingDriverBusinessLine(MarketingDriverBusinessLine marketingDriverBusinessLine);


    /**
     * 根据id修改司机预约线路信息
     * @param driverBusinessLineInfo
     * @autor nxj
     */
    public boolean updateDriverBusinessLineInfo(DriverBusinessLineInfo driverBusinessLineInfo);


    /**
     * 保存客户司机预约线路
     * @param marketingDriverBusinessLine
     * @autor nxj
     */
    public Integer addMarketingDriverBusinessLine(MarketingDriverBusinessLine marketingDriverBusinessLine);


    /**
     * 保存司机预约线路
     * @param driverBusinessLineInfo
     * @autor nxj
     */
    public Integer addDriverBusinessLineInfo(DriverBusinessLineInfo driverBusinessLineInfo);

}

