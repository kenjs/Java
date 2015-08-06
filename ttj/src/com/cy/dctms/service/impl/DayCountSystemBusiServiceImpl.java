package com.cy.dctms.service.impl;

import com.cy.dctms.common.bo.DayCountSystemBusi;
import com.cy.dctms.dao.DayCountSystemBusiDao;
import com.cy.dctms.service.DayCountSystemBusiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;

@Service("dayCountSyatemBusiService")
public class DayCountSystemBusiServiceImpl implements DayCountSystemBusiService {

    @Resource
    private DayCountSystemBusiDao dayCountSystemBusiDao;

    @Override
    public void countSystemBusiData() throws SQLException {
        int appDowns = dayCountSystemBusiDao.queryDailyAppDownloadsNum();
        int enddayRegAppusers = dayCountSystemBusiDao.queryAppRegisterNumByNow();
        int enddayRegWebusers = dayCountSystemBusiDao.queryWebRegisterNumByNow();
        int regDrivers = dayCountSystemBusiDao.queryDailyRegisterDriversNum();
        int regUsers = dayCountSystemBusiDao.queryDailyCompanyRegisterNum();
        int supplyReleases = dayCountSystemBusiDao.queryDailyCompanyReleaseCargoNum();
        int supplySelf = dayCountSystemBusiDao.queryDailyImportCargoNum();
        int forOrders = dayCountSystemBusiDao.queryDailyBookCarsNum();
        int enddayAuthUsers = dayCountSystemBusiDao.queryEnddayAuthUsers();
        int enddayAuthDrivers = dayCountSystemBusiDao.queryEnddayAuthDrivers();

        //日存活数
        int daySurvival = dayCountSystemBusiDao.queryDriverDaySurvival();
        //日活跃数
        int dayActive = dayCountSystemBusiDao.queryDriverDayActive();
        //周存活数
        int weekSurvival = dayCountSystemBusiDao.queryDriverWeekSurvival();
        //周活跃数
        int weekActive = dayCountSystemBusiDao.queryDriverWeekActive();
        //月存活数
        int monthSurvival = dayCountSystemBusiDao.queryDriverMonthSurvival();
        //月活跃数
        int monthActive = dayCountSystemBusiDao.queryDriverMonthActive();
        //2个月存活数
        int month2Survival = dayCountSystemBusiDao.queryDriverMonth2Survival();
        //2个月活跃数
        int month2Active = dayCountSystemBusiDao.queryDriverMonth2Active();

        if (appDowns == 0 && enddayRegAppusers == 0 && enddayRegWebusers == 0 && regDrivers == 0 && regUsers == 0 && supplyReleases == 0 && supplySelf == 0 && forOrders == 0 && enddayAuthUsers == 0 && enddayAuthDrivers == 0 && daySurvival == 0 && dayActive == 0 && weekSurvival == 0 && weekActive == 0 && monthSurvival == 0 && monthActive == 0 && month2Survival == 0 && month2Active == 0) {
            return;
        }

        DayCountSystemBusi dayCountSystemBusi = new DayCountSystemBusi();
        dayCountSystemBusi.setAppDowns(appDowns);
        dayCountSystemBusi.setEnddayRegAppusers(enddayRegAppusers);
        dayCountSystemBusi.setEnddayRegWebusers(enddayRegWebusers);
        dayCountSystemBusi.setRegDrivers(regDrivers);
        dayCountSystemBusi.setRegUsers(regUsers);
        dayCountSystemBusi.setSupplyReleases(supplyReleases);
        dayCountSystemBusi.setSupplySelf(supplySelf);
        dayCountSystemBusi.setForOrders(forOrders);
        dayCountSystemBusi.setEnddayAuthUsers(enddayAuthUsers);
        dayCountSystemBusi.setEnddayAuthDrivers(enddayAuthDrivers);

        dayCountSystemBusi.setDaySurvivalDrivers(daySurvival);
        dayCountSystemBusi.setDayActiveDrivers(dayActive);
        dayCountSystemBusi.setWeekSurvivalDrivers(weekSurvival);
        dayCountSystemBusi.setWeekActiveDrivers(weekActive);
        dayCountSystemBusi.setMonthSurvivalDrivers(monthSurvival);
        dayCountSystemBusi.setMonthActiveDrivers(monthActive);
        dayCountSystemBusi.setMonth2SurvivalDrivers(month2Survival);
        dayCountSystemBusi.setMonth2ActiveDrivers(month2Active);

        dayCountSystemBusiDao.countDaySystemBusi(dayCountSystemBusi);
    }

}
