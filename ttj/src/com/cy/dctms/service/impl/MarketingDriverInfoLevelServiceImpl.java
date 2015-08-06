package com.cy.dctms.service.impl;

import com.cy.dctms.common.bo.MarketingDriverLevelChangeRecord;
import com.cy.dctms.common.util.DateUtils;
import com.cy.dctms.dao.DriverUserInfoDao;
import com.cy.dctms.service.MarketingDriverInfoLevelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haoy on 2014/12/23.
 */
@Service("marketingDriverInfoLevelService")
public class MarketingDriverInfoLevelServiceImpl implements MarketingDriverInfoLevelService {

    @Resource
    private DriverUserInfoDao driverUserInfoDao;

    @Override
    public void driverLevelUpdate() throws Exception {
        Map<String, String> map;
        MarketingDriverLevelChangeRecord record;
        //认证通过的司机
        List<String> list = driverUserInfoDao.querySubmitedDriverId();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                String driverId = list.get(i);

                String realLevel = driverUserInfoDao.queryMarketingDriverLevel(driverId);
                String assisterId = driverUserInfoDao.queryMarketingAssisterId(driverId);

                record = new MarketingDriverLevelChangeRecord();
                record.setBeforeChangeLevel(realLevel);
                record.setBeforeChangeAssisterId(assisterId);

                map = new HashMap<String, String>();
                map.put("driverId", driverId);
                map.put("realLevel", "A");
                driverUserInfoDao.updateMarketingDriverInfo(map);

                //保存变更记录
                record.setAfterChangeLevel("A");
                record.setCustomerId(driverId);
                record.setChangeDate(DateUtils.getCurrentDate());
                driverUserInfoDao.addMarketingDriverLevelChangeRecord(record);
            }
        }

        //有经营线路的司机
        List<String> listLine = driverUserInfoDao.queryDriverDriverLineCount();
        if (listLine != null && listLine.size() > 0) {
            for (int i = 0; i < listLine.size(); i++) {
                String driverId = listLine.get(i);

                String realLevel = driverUserInfoDao.queryMarketingDriverLevel(driverId);
                String assisterId = driverUserInfoDao.queryMarketingAssisterId(driverId);

                record = new MarketingDriverLevelChangeRecord();
                record.setBeforeChangeLevel(realLevel);
                record.setBeforeChangeAssisterId(assisterId);

                map = new HashMap<String, String>();
                map.put("driverId", driverId);
                map.put("realLevel", "B");
                driverUserInfoDao.updateMarketingDriverInfo(map);

                //保存变更记录
                record.setAfterChangeLevel("B");
                record.setCustomerId(driverId);
                record.setChangeDate(DateUtils.getCurrentDate());
                driverUserInfoDao.addMarketingDriverLevelChangeRecord(record);
            }
        }

        //月存活的司机
        List<String> listActive = driverUserInfoDao.queryDriverAlive();
        if (listActive != null && listActive.size() > 0) {
            for (int i = 0; i < listActive.size(); i++) {
                String driverId = listActive.get(i);

                String assisterId = driverUserInfoDao.queryMarketingAssisterId(driverId);

                record = new MarketingDriverLevelChangeRecord();
                record.setBeforeChangeAssisterId(assisterId);

                map = new HashMap<String, String>();
                map.put("driverId", driverId);
                map.put("realLevel", "C");
                driverUserInfoDao.updateMarketingDriverInfo(map);

                //保存变更记录
                record.setBeforeChangeLevel("D");
                record.setAfterChangeLevel("C");
                record.setCustomerId(driverId);
                record.setChangeDate(DateUtils.getCurrentDate());
                driverUserInfoDao.addMarketingDriverLevelChangeRecord(record);
            }
        }
    }
}
