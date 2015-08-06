package com.cy.dctms.service.impl;

import com.cy.dctms.common.bo.DaycountDriverUserBusi;
import com.cy.dctms.dao.DaycountDriverUserBusiDao;
import com.cy.dctms.service.DaycountDriverUserBusiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by haoy on 2014/9/5.
 */
@Service("daycountDriverUserBusiService")
public class DaycountDriverUserBusiServiceImpl implements DaycountDriverUserBusiService {

    @Resource
    private DaycountDriverUserBusiDao daycountDriverUserBusiDao;

    @Override
    public void countDriverUserBusiData() throws Exception {
        //待保存的司机用户当天数据统计对象列表
        List<DaycountDriverUserBusi> list = new ArrayList<DaycountDriverUserBusi>();
        //当天有统计数据发生的司机用户ID列表
        List<Integer> driverIds = daycountDriverUserBusiDao.queryDriverListForDataCount();
        if (driverIds == null || driverIds.size() == 0) {
            return;
        }

        for (int i = 0; i < driverIds.size(); i++) {
            if (null == driverIds.get(i)) {
                continue;
            }
            int driverId = driverIds.get(i);
            int noConfirmOrders = daycountDriverUserBusiDao.queryNoConfirmOrders(driverId);
            int noAssessmentOrders = daycountDriverUserBusiDao.queryNoAssessmentOrders(driverId);
            int supplyFinds = daycountDriverUserBusiDao.querySupplyFinds(driverId);
            int priceQuotes = daycountDriverUserBusiDao.queryPriceQuotes(driverId);
            int priceQuotesSucceed = daycountDriverUserBusiDao.queryPriceQuotesSucceed(driverId);
            int forOrders = daycountDriverUserBusiDao.queryForOrders(driverId);
            int transactions = daycountDriverUserBusiDao.queryTransactions(driverId);
            int callCargoNum = daycountDriverUserBusiDao.queryCallCargoNum(driverId);
            int callTransactionNum = daycountDriverUserBusiDao.queryCallTransactionNum(driverId);

            if (noConfirmOrders == 0 && noAssessmentOrders == 0 && supplyFinds == 0
                    && priceQuotes == 0 && forOrders == 0 && transactions == 0 && callCargoNum == 0 && callTransactionNum == 0) {
                continue;
            }
            DaycountDriverUserBusi daycountDriverUserBusi = new DaycountDriverUserBusi();
            daycountDriverUserBusi.setDriverId(driverId);
            daycountDriverUserBusi.setNoConfirmOrders(noConfirmOrders);
            daycountDriverUserBusi.setNoAssessmentOrders(noAssessmentOrders);
            daycountDriverUserBusi.setSupplyFinds(supplyFinds);
            daycountDriverUserBusi.setPriceQuotes(priceQuotes);
            daycountDriverUserBusi.setPriceQuotesSucceed(priceQuotesSucceed);
            daycountDriverUserBusi.setForOrders(forOrders);
            daycountDriverUserBusi.setTransactions(transactions);
            daycountDriverUserBusi.setCallCargoNum(callCargoNum);
            daycountDriverUserBusi.setCallTransactionNum(callTransactionNum);

            list.add(daycountDriverUserBusi);
        }

        // 批量保存
        if (list.size() > 0) {
            daycountDriverUserBusiDao.insertDaycountDriverUserBusi(list);
        }
    }

}
