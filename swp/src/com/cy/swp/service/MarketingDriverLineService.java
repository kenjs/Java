package com.cy.swp.service;

import com.cy.swp.bo.DriverLineInfo;
import com.cy.swp.bo.MarketingDriverLine;

import java.util.List;
import java.util.Map;

/**
 * 司机运营线路Service
 * Created by nixianjing on 14/12/10.
 */
public interface MarketingDriverLineService {

    /**
     * 根据客户id查询客户司机线路信息，返回List对象
     * @param id
     * @autor nxj
     */
    public List<MarketingDriverLine> queryMarketingDriverLineByCustomerDriverId(String id);


    /**
     * 根据司机id查询司机线路信息，返回List对象
     * @param driverId
     * @autor nxj
     */
    public List<DriverLineInfo> queryDriverLineInfoByDriverId(String driverId);

    /**
     * 根据id获取司机客户运营线路
     * @param id
     * @autor nxj
     */
    public MarketingDriverLine queryMarketingDriverLineById(Integer id);

    /**
     * 根据id获取司机运营线路
     * @param id
     * @autor nxj
     */
    public DriverLineInfo queryDriverLineInfoById(Integer id);


    /**
     * 修改客户司机运营线路
     * @param marketingDriverLine
     * @autor nxj
     */
    public boolean updateMarketingDriverLine(MarketingDriverLine marketingDriverLine);


    /**
     * 修改司机运营线路
     * @param driverLineInfo
     * @autor nxj
     */
    public boolean updateDriverLineInfo(DriverLineInfo driverLineInfo);


    /**
     * 保存客户司机运营线路
     * @param marketingDriverLine
     * @autor nxj
     */
    public Integer addMarketingDriverLine(MarketingDriverLine marketingDriverLine);


    /**
     * 保存司机运营线路
     * @param driverLineInfo
     * @autor nxj
     */
    public Integer addDriverLineInfo(DriverLineInfo driverLineInfo);
}
