package com.cy.dctms.timeTask;

import com.cy.dctms.common.util.DateUtils;
import com.cy.dctms.service.DaycountDriverUserBusiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 按天统计司机业务数
 * Created by haoy on 2014/9/10.
 */
public class DaycountDriverUserBusiTask {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private DaycountDriverUserBusiService daycountDriverUserBusiService;

    public void execute() throws Exception {
        try {
            String dataCountedDate = DateUtils.getBeforeDay();
            if (logger.isInfoEnabled()) {
                logger.info("统计" + dataCountedDate + "司机业务数据开始,当前时间：" + DateUtils.getCurrentDateTime());
            }
            daycountDriverUserBusiService.countDriverUserBusiData();
            if (logger.isInfoEnabled()) {
                logger.info("统计" + dataCountedDate + "司机业务数据完成,当前时间：" + DateUtils.getCurrentDateTime());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
