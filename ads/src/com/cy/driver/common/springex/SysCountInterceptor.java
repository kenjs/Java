package com.cy.driver.common.springex;

import com.cy.driver.common.countevent.Count;
import com.cy.driver.common.threadObject.count.CountRecord;
import com.cy.driver.dao.CountWebUserBusiDao;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 系统总业务数据实时计数拦截器
 * Created by haoy on 2014/9/25.
 */
public class SysCountInterceptor extends HandlerInterceptorAdapter{
    
    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private CountRecord countRecorder;

    @Resource
    private CountWebUserBusiDao countWebUserBusiDao;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if (log.isDebugEnabled()) {
            log.debug("开始统计司机业务数");
        }

        try {
			if (handler instanceof HandlerMethod) {
			    HandlerMethod handlerMethod = (HandlerMethod) handler;
			    Method method = handlerMethod.getMethod();
			    Count count = method.getAnnotation(Count.class);

			    if (count != null) {
			        String driverIdStr = request.getParameter("driverId");
			        String requestUri = request.getRequestURI();

			        int driverId = 0;
			        if (StringUtils.isNotBlank(driverIdStr)) {
			            driverId = Integer.parseInt(driverIdStr);
			        }
			        com.cy.driver.common.threadObject.count.Count model = null;
			        String[] tableNames = count.tableNames();
			        String[] columns = count.columns();
			        int[] types = count.operaType();

			        if (tableNames.length > 0) {
			            for (int i = 0; i < tableNames.length; i++) {

			                String uri = requestUri.substring(requestUri.lastIndexOf("/") + 1);
			                if ("addNewDriverUserAssessAction".equals(uri)) {
			                    String score = request.getParameter("assessEvaluateScore");

			                    String id = request.getParameter("transactionId");
			                    int webUserId = countWebUserBusiDao.queryWebUserIdById(id);

			                    if ("3".equals(score)) {
			                        model = new com.cy.driver.common.threadObject.count.Count();

			                        model.setDriverId(webUserId);
			                        model.setTableName(tableNames[0]);
			                        model.setColumn(columns[0]);
			                        model.setOperaType(0);

			                        countRecorder.addCountToQueue(model);
			                    } else if ("6".equals(score)) {
			                        model = new com.cy.driver.common.threadObject.count.Count();

			                        model.setDriverId(webUserId);
			                        model.setTableName(tableNames[1]);
			                        model.setColumn(columns[1]);
			                        model.setOperaType(0);

			                        countRecorder.addCountToQueue(model);
			                    } else {
			                        model = new com.cy.driver.common.threadObject.count.Count();

			                        model.setDriverId(webUserId);
			                        model.setTableName(tableNames[2]);
			                        model.setColumn(columns[2]);
			                        model.setOperaType(0);

			                        countRecorder.addCountToQueue(model);
			                    }
			                    model = new com.cy.driver.common.threadObject.count.Count();

			                    model.setDriverId(driverId);
			                    model.setTableName(tableNames[3]);
			                    model.setColumn(columns[3]);
			                    model.setOperaType(1);

			                    countRecorder.addCountToQueue(model);

			                    break;
			                } else if ("driverTelephoneAction".equals(uri)) {
			                    String type = request.getParameter("type");
			                    if ("0".equals(type)) {
			                        model = new com.cy.driver.common.threadObject.count.Count();

			                        model.setDriverId(driverId);
			                        model.setTableName(tableNames[1]);
			                        model.setColumn(columns[1]);
			                        model.setOperaType(0);

			                        countRecorder.addCountToQueue(model);
			                    } else {
			                        model = new com.cy.driver.common.threadObject.count.Count();

			                        model.setDriverId(driverId);
			                        model.setTableName(tableNames[0]);
			                        model.setColumn(columns[0]);
			                        model.setOperaType(0);

			                        countRecorder.addCountToQueue(model);
			                    }
			                    break;
			                } else if ("unloadCargoAction".equals(uri)) {
			                    String tradeStart = request.getParameter("tradeStart");
			                    if ("5".equals(tradeStart)) {
			                        model = new com.cy.driver.common.threadObject.count.Count();

			                        model.setDriverId(driverId);
			                        model.setTableName(tableNames[0]);
			                        model.setColumn(columns[0]);
			                        model.setOperaType(0);

			                        countRecorder.addCountToQueue(model);

			                        model = new com.cy.driver.common.threadObject.count.Count();

			                        model.setDriverId(driverId);
			                        model.setTableName(tableNames[1]);
			                        model.setColumn(columns[1]);
			                        model.setOperaType(0);

			                        countRecorder.addCountToQueue(model);

			                        model = new com.cy.driver.common.threadObject.count.Count();

			                        model.setDriverId(driverId);
			                        model.setTableName(tableNames[2]);
			                        model.setColumn(columns[2]);
			                        model.setOperaType(1);

			                        countRecorder.addCountToQueue(model);

			                        model = new com.cy.driver.common.threadObject.count.Count();

			                        String id = request.getParameter("id");
			                        int webUserId = countWebUserBusiDao.queryWebUserIdById(id);

			                        model.setDriverId(webUserId);
			                        model.setTableName("t_count_web_user_busi");
			                        model.setColumn("no_confirm_receives");
			                        model.setOperaType(0);

			                        countRecorder.addCountToQueue(model);
			                    } else if ("1".equals(tradeStart)) {
			                        model = new com.cy.driver.common.threadObject.count.Count();

			                        model.setDriverId(driverId);
			                        model.setTableName(tableNames[2]);
			                        model.setColumn(columns[2]);
			                        model.setOperaType(0);

			                        countRecorder.addCountToQueue(model);
			                    } else if ("6".equals(tradeStart)) {
			                        model = new com.cy.driver.common.threadObject.count.Count();

			                        model.setDriverId(driverId);
			                        model.setTableName(tableNames[2]);
			                        model.setColumn(columns[2]);
			                        model.setOperaType(1);

			                        countRecorder.addCountToQueue(model);

			                        model = new com.cy.driver.common.threadObject.count.Count();

			                        String id = request.getParameter("id");
			                        int webUserId = countWebUserBusiDao.queryWebUserIdById(id);

			                        model.setDriverId(webUserId);
			                        model.setTableName("t_count_web_user_busi");
			                        model.setColumn("no_confirm_orders");
			                        model.setOperaType(1);

			                        countRecorder.addCountToQueue(model);
			                    } else if ("3".equals(tradeStart)) {
			                        model = new com.cy.driver.common.threadObject.count.Count();

			                        String id = request.getParameter("id");
			                        int webUserId = countWebUserBusiDao.queryWebUserIdById(id);

			                        model.setDriverId(webUserId);
			                        model.setTableName("t_count_web_user_busi");
			                        model.setColumn("no_confirm_orders");
			                        model.setOperaType(1);

			                        countRecorder.addCountToQueue(model);

			                        model = new com.cy.driver.common.threadObject.count.Count();

			                        model.setDriverId(driverId);
			                        model.setTableName("t_count_driver_user_busi");
			                        model.setColumn("no_confirm_orders");
			                        model.setOperaType(1);

			                        countRecorder.addCountToQueue(model);
			                    }

			                    break;
			                } else if ("uploadFile".equals(uri)) {
			                    int auditFlag = countWebUserBusiDao.queryDriverUserAuditFlagById(driverId);
			                    if (auditFlag == 1) {
			                        model = new com.cy.driver.common.threadObject.count.Count();

			                        model.setDriverId(driverId);
			                        model.setTableName("t_count_system_busi");
			                        model.setColumn("total_auth_drivers");
			                        model.setOperaType(1);

			                        countRecorder.addCountToQueue(model);
			                    }
			                } else if ("searchCargoAction".equals(uri)) {
								String way = request.getParameter("way");
								if ("1".equals(way)) {
									model = new com.cy.driver.common.threadObject.count.Count();

									model.setDriverId(driverId);
									model.setTableName("t_count_driver_user_busi");
									model.setColumn("supply_finds");
									model.setOperaType(0);

									countRecorder.addCountToQueue(model);

									model = new com.cy.driver.common.threadObject.count.Count();

									model.setDriverId(driverId);
									model.setTableName("t_count_system_busi");
									model.setColumn("total_supply_finds");
									model.setOperaType(0);

									countRecorder.addCountToQueue(model);
								}
							} else {

			                    model = new com.cy.driver.common.threadObject.count.Count();

			                    model.setDriverId(driverId);
			                    model.setTableName(tableNames[i]);
			                    model.setColumn(columns[i]);
			                    model.setOperaType(types[i]);

			                    countRecorder.addCountToQueue(model);
			                }

			            }
			        }
			    }
			}
		} catch (Exception e) {
			log.info("事件点拦截中出现错误: " + e.getMessage());
			log.error(e.getMessage());
		}

        super.postHandle(request, response, handler, modelAndView);
    }

}
