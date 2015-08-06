package com.cy.driver.common.syslog;

import com.cy.driver.bo.OperationLogInfoBo;
import com.cy.driver.common.threadObject.log.LogService;
import com.cy.driver.dao.OperationLogDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by haoy on 2014/9/22.
 */
@Service("logService")
public class LogServiceImpl implements LogService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private OperationLogDao operationLogDao;

    @Override
    public int insertOperationLog(List<OperationLogInfoBo> list) throws SQLException {
        int saveCount = 0;
        if (null != list && 0 != list.size()) {
            saveCount = operationLogDao.insertOperationLogBatch(list);
            if (log.isInfoEnabled()) {
                log.info("记录日志对象结束, 共" + saveCount + "条！");
            }
        }
        return saveCount;
    }
}
