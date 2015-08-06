package com.cy.driver.common.threadObject.count;

import com.cy.driver.dao.AppReleasesDao;
import com.cy.driver.dao.CountDriverUserBusiDao;
import com.cy.driver.dao.CountSystemBusiDao;
import com.cy.driver.dao.CountWebUserBusiDao;
import com.cy.driver.domain.CountDriverUserBusiDomain;
import com.cy.driver.domain.CountSystemBusiDomain;
import com.cy.driver.domain.CountWebUserBusiDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haoy on 2014/10/8.
 */
@Service("countService")
public class CountServiceImpl implements CountService {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	private CountDriverUserBusiDao countDriverUserBusiDao;

	@Resource
	private CountSystemBusiDao countSystemBusiDao;

    @Resource
    private AppReleasesDao appReleasesDao;

    @Resource
    private CountWebUserBusiDao countWebUserBusiDao;

    @Override
    public int updateCount(List<Count> counts) throws SQLException {
    	if (log.isInfoEnabled()) {
			log.info("保存系统总业务开始......");
		}

    	Map<String, Object> map;

    	for (Count count : counts) {
    		map = new HashMap<String, Object>();

    		String tableName = count.getTableName();
            int driverId = count.getDriverId();
    		String column = count.getColumn();
            int type = count.getOperaType();

			map.put("tableName", tableName);
			//map.put("", objects[2]);
			map.put("driverId", driverId);

			if ("t_count_driver_user_busi".equals(tableName)) {
				CountDriverUserBusiDomain domain = countDriverUserBusiDao.queryCountDriverUserBusi(driverId);
				if (domain == null) {
					map.put("column", column + "," + "driver_id");
					map.put("value", 1 + "," + driverId);
					countDriverUserBusiDao.insertCountDriverUserBusi(map);
				} else {
					map.put("column", column);

                    int value;
					int finds = countDriverUserBusiDao.queryCountDriverUserBusiColumn(map);
                    if (type == 1) {
                        value = finds - 1 < 0 ? 0 : finds - 1;
                    } else {
                        value = finds + 1;
                    }
					map.put("value", value);
					countDriverUserBusiDao.updateCountDriverUserBusi(map);
				}
			} else if ("t_count_system_busi".equals(tableName)) {
				CountSystemBusiDomain countSystemBusiDomain = countSystemBusiDao.queryCountSystemBusi();
				if (countSystemBusiDomain == null) {
					map.put("column", column);
					map.put("value", 1);

					countSystemBusiDao.insertCountSystemBusi(map);
				} else {
					map.put("column", column);

                    int value;
					int finds = countSystemBusiDao.queryCountSystemBusiColumn(column);
                    if (type == 1) {
                        value = finds - 1 < 0 ? 0 : finds - 1;
                    } else {
                        value = finds + 1;
                    }
					map.put("value", value);

					countSystemBusiDao.updateCountSystemBusi(map);
				}
			} else if("t_app_releases".equals(tableName)) {
                long id = appReleasesDao.queryAppReleaseMaxId();
                int downCount = appReleasesDao.queryAppReleaseDownByMaxId(id);

                map.put("id", id);
                map.put("downCount", downCount + 1);

                appReleasesDao.updateAppReleaseDownById(map);
            } else if("t_count_web_user_busi".equals(tableName)) {
                CountWebUserBusiDomain domain = countWebUserBusiDao.queryCountWebUserBusiDomainByWebUserId(driverId);
                if (domain == null) {
                    map.put("column", column + "," + "web_user_id");
                    map.put("value", 1 + "," + driverId);

                    countWebUserBusiDao.insertCountWebUserBusiC(map);
                } else {
                    int value;
                    int finds = countWebUserBusiDao.queryCountWebUserBusiColumnByWebUserId(map);
                    if (type == 1) {
                        value = finds - 1 < 0 ? 0 : finds - 1;
                    } else {
                        value = finds + 1;
                    }
                    map.put("value", value);
                    countWebUserBusiDao.updateCountWebUserBusiByWebUserId(map);
                }
            }

		}

        if (log.isInfoEnabled()) {
            log.info("保存系统总业务结束.");
        }

        return 0;
    }
}
