package com.cy.dctms.timeTask;

import com.cy.dctms.dao.LocationCollectLastInfoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * 历史位置信息移动
 * Created by haoy on 2014/11/27.
 */
public class HistoryLocationTask {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    @Resource
    LocationCollectLastInfoDao locationCollectLastInfoDao;

    public void execute() throws Exception {
        if (LOG.isDebugEnabled()) {
            LOG.debug("开始清理...");
        }

        try {
            //移动
            locationCollectLastInfoDao.addLastLocation();

            //删除
            locationCollectLastInfoDao.deleteHistoryLocation();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
    }
}
