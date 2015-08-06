package com.cy.dctms.service.impl;

import com.cy.dctms.common.bo.DaycountDriverActive;
import com.cy.dctms.dao.DaycountDriverActiveDao;
import com.cy.dctms.dao.DaycountDriverUserBusiDao;
import com.cy.dctms.service.DaycountDriverActiveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by haoy on 2014/9/23.
 */
@Service("daycountDriverActiveService")
public class DaycountDriverActiveServiceImpl implements DaycountDriverActiveService {

    @Resource
    private DaycountDriverActiveDao daycountDriverActiveDao;
    @Resource
    private DaycountDriverUserBusiDao daycountDriverUserBusiDao;

    @Override
    public void countDriverActiveData() throws Exception {
        //当天发生过位置上传或打开APP行为的司机用户ID列表
        List<Integer> driverIds = daycountDriverUserBusiDao.queryDriverListForActive();
        if (driverIds == null || driverIds.size() == 0) {
            return;
        }

        List<DaycountDriverActive> updateList = new ArrayList<DaycountDriverActive>();  //待更新数据集合
        List<DaycountDriverActive> insertList = new ArrayList<DaycountDriverActive>();  //待保存数据集合
        for (int driverId : driverIds) {
            int initiativeLinks = daycountDriverActiveDao.selectDriverDayInitiativeLinks(driverId);
            int huoyuanPushs = daycountDriverActiveDao.selectDriverDayHuoyuanPushs(driverId);
            int locationUps = daycountDriverActiveDao.selectDriverDayLocationUps(driverId);
            int passiveLinks = huoyuanPushs + locationUps;
            DaycountDriverActive daycountDriverActive = new DaycountDriverActive();
            daycountDriverActive.setDriverId(driverId);
            daycountDriverActive.setInitiativeLinks(initiativeLinks);
            daycountDriverActive.setPassiveLinks(passiveLinks);
            daycountDriverActive.setAllLinks(initiativeLinks + passiveLinks);

            int isHaving = daycountDriverActiveDao.selectDaycountDriverActive(driverId);
            if (isHaving > 0) {//司机当天已经存在，更新
                updateList.add(daycountDriverActive);
            } else {
                insertList.add(daycountDriverActive);
            }
        }
        if (updateList.size() > 0) {
            daycountDriverActiveDao.updateDaycountDriverActive(updateList);
        }
        if (insertList.size() > 0) {
            daycountDriverActiveDao.insertDaycountDriverActive(insertList);
        }
    }
}
