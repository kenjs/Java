package com.cy.swp.service;

import com.cy.swp.bo.DriverBusinessLineInfo;
import com.cy.swp.bo.MarketingDriverBusinessLine;
import com.cy.swp.domain.MarketingDriverBusinessLineDomain;

import java.util.List;
import java.util.Map;

/**
 * Created by nixianjing on 14/12/16.
 */
public interface MarketingDriverBusinessLineService {

    public List<MarketingDriverBusinessLineDomain> queryMarketingDriverBusinessLineByCustomerDriverIdList(Integer customerDriverId);

    public List<MarketingDriverBusinessLineDomain> queryDriverBusinessLineInfoByIdList(Integer customerDriverId);


    /**
     * 根据客户id获取客户司机预约线路
     * @param customerDriverId
     * @autor nxj
     */
    public String queryMarketingDriverBusinessLineByCustomerDriverId(Integer customerDriverId);


    /**
     * 根据司机id获取司机预约线路
     * @param drriverId
     * @autor nxj
     */
    public String queryDriverBusinessLineInfoByDriverId(Integer drriverId);



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
