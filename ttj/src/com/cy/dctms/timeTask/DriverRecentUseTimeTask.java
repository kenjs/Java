package com.cy.dctms.timeTask;

import com.cy.dctms.dao.DriverUserInfoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by haoy on 2014/12/23.
 */
public class DriverRecentUseTimeTask {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private DriverUserInfoDao driverUserInfoDao;

    public void execute() {
        if (log.isInfoEnabled()) {
            log.info("司机最近使用时间定时任务开始执行.....");
        }
        Map<String, String> map;
        try {
            List<Map<String, String>> list = driverUserInfoDao.queryDriverUserFromLog();

            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    map = list.get(i);
                    if (map != null) {
                        driverUserInfoDao.updateDriverUserInfoById(map);
                    }
                }
            }

        } catch (SQLException e) {
            log.error("统计司机最近使用时间时出错：" + e.getMessage());
        }
        if (log.isInfoEnabled()) {
            log.info("司机最近使用时间定时任务结束执行.....");
        }
    }

}
