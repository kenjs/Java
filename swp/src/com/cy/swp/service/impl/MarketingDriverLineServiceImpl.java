package com.cy.swp.service.impl;

import com.cy.swp.bo.DriverLineInfo;
import com.cy.swp.bo.MarketingDriverLine;
import com.cy.swp.common.constants.Constants;
import com.cy.swp.dao.MarketingDriverLineDao;
import com.cy.swp.service.MarketingDriverLineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 司机运营线路ServiceImpl
 * Created by nixianjing on 14/12/10.
 */
@Service("marketingDriverLineServiceImpl")
public class MarketingDriverLineServiceImpl implements MarketingDriverLineService {

    @Resource
    private MarketingDriverLineDao marketingDriverLineDao;

    @Override
    public MarketingDriverLine queryMarketingDriverLineById(Integer id) {
        return marketingDriverLineDao.queryMarketingDriverLineById(id);
    }

    @Override
    public DriverLineInfo queryDriverLineInfoById(Integer id) {
        return marketingDriverLineDao.queryDriverLineInfoById(id);
    }

    @Override
    public List<MarketingDriverLine> queryMarketingDriverLineByCustomerDriverId(String id) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("customerDriverId",id);
        queryMap.put("deleteFlag", Constants.DELETED_FLAG_FALSE);
        return marketingDriverLineDao.queryMarketingDriverLineByCustomerDriverId(queryMap);
    }

    @Override
    public List<DriverLineInfo> queryDriverLineInfoByDriverId(String driverId) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("driverId",driverId);
        queryMap.put("start", Constants.DELETED_FLAG_FALSE);
        return marketingDriverLineDao.queryDriverLineInfoByDriverId(queryMap);
    }

    @Override
    public boolean updateMarketingDriverLine(MarketingDriverLine marketingDriverLine) {
        return marketingDriverLineDao.updateMarketingDriverLine(marketingDriverLine);
    }

    @Override
    public boolean updateDriverLineInfo(DriverLineInfo driverLineInfo) {
        return marketingDriverLineDao.updateDriverLineInfo(driverLineInfo);
    }

    @Override
    public Integer addMarketingDriverLine(MarketingDriverLine marketingDriverLine) {
        marketingDriverLine.setDeleteFlag(Constants.DELETED_FLAG_FALSE);
        marketingDriverLine.setLineType(Constants.DRIVER_LINE_SHUANG_KEY);
        return marketingDriverLineDao.addMarketingDriverLine(marketingDriverLine);
    }

    @Override
    public Integer addDriverLineInfo(DriverLineInfo driverLineInfo) {
        driverLineInfo.setStart(Constants.DELETED_FLAG_FALSE);
        driverLineInfo.setLineType(Constants.DRIVER_LINE_SHUANG_KEY);
        return marketingDriverLineDao.addDriverLineInfo(driverLineInfo);
    }
}
