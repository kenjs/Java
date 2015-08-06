package com.cy.dctms.service.impl;

import com.cy.dctms.common.bo.DaycountWebUserBusi;
import com.cy.dctms.dao.DaycountWebUserBusiDao;
import com.cy.dctms.service.DaycountWebUserBusiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by haoy on 2014/9/5.
 */
@Service("daycountWebUserBusiService")
public class DaycountWebUserBusiServiceImpl implements DaycountWebUserBusiService {

    @Resource
    private DaycountWebUserBusiDao daycountWebUserBusiDao;

    @Override
    public void countWebUserBusiData() throws Exception {
        //待保存的企业用户当天数据统计对象列表
        List<DaycountWebUserBusi> list = new ArrayList<DaycountWebUserBusi>();
        //当天有统计数据发生的Web用户ID列表
        List<Integer> webUserIds = daycountWebUserBusiDao.queryCompanyList();
        if (webUserIds == null || webUserIds.size() == 0) {
            return;
        }

        for (int i = 0; i < webUserIds.size(); i++) {
            int webUserId = webUserIds.get(i);
            int noConfirmOrders = daycountWebUserBusiDao.queryNoConfirmOrders(webUserId);
            int noConfirmReceives = daycountWebUserBusiDao.queryNoConfirmReceives(webUserId);
            int noAssessmentOrders = daycountWebUserBusiDao.queryNoAssessmentOrders(webUserId);
            int supplyReleases = daycountWebUserBusiDao.querySupplyReleases(webUserId);
            int truckOrders = daycountWebUserBusiDao.queryTruckOrders(webUserId);
            int transactions = daycountWebUserBusiDao.queryTransactions(webUserId);

            if (noConfirmOrders == 0 && noConfirmReceives == 0 &&
                    noAssessmentOrders == 0 && supplyReleases == 0 && truckOrders == 0 && transactions == 0) {
                continue;
            }
            DaycountWebUserBusi daycountWebUserBusi = new DaycountWebUserBusi();
            daycountWebUserBusi.setWebUserId(webUserId);
            daycountWebUserBusi.setNoConfirmOrders(noConfirmOrders);
            daycountWebUserBusi.setNoConfirmReceives(noConfirmReceives);
            daycountWebUserBusi.setNoAssessmentOrders(noAssessmentOrders);
            daycountWebUserBusi.setSupplyReleases(supplyReleases);
            daycountWebUserBusi.setTruckOrders(truckOrders);
            daycountWebUserBusi.setTransactions(transactions);

            list.add(daycountWebUserBusi);

        }

        //批量保存
        if (list.size() > 0) {
            daycountWebUserBusiDao.insertDaycountWebUserBusi(list);
        }
    }
}
