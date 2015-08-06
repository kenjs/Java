package com.cy.driver.common.springex;

import com.cy.driver.common.syslog.Log;
import com.cy.driver.common.syslog.LogEnum;
import com.cy.driver.common.threadObject.log.LogBo;
import com.cy.driver.common.threadObject.log.LogRecorder;
import com.cy.driver.common.util.DateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
 * 系统日志计录拦截器
 * Created by haoy on 2014/9/22.
 */
public class SysLogInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private LogRecorder logRecorder;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if (logger.isDebugEnabled()) {
			logger.debug("日志拦截开始...........");
        }

        try {
			if (handler instanceof HandlerMethod) {
			    HandlerMethod handlerMethod = (HandlerMethod) handler;
			    Method method = handlerMethod.getMethod();
			    Log log = method.getAnnotation(Log.class);
			    if (log != null) {

			        String currenTime = DateUtil.getCurrentDateTime();

			        LogBo logBo = new LogBo();
			        logBo.setCreateTime(currenTime);

			        String driverId = request.getParameter("driverId");

			        if (StringUtils.isNotBlank(driverId)) {
			            logBo.setUserDriverId(Integer.parseInt(driverId));
			        }

			        int type = log.type();

					boolean ignore = false;

			        if (type == 78) {
			            String typeStr = request.getParameter("type");
			            if ("0".equals(typeStr)) {
			                logBo.setOperationType(81);
			                logBo.setOperationName("driverTelephoneAction");
			                logBo.setRemark("司机拨打电话咨询订单");
			            } else {
			                logBo.setOperationType(type);
			                logBo.setOperationName("driverTelephoneAction");
			                logBo.setRemark("司机拨打电话咨询货源");
			            }
			        } else if (type == 82) {
						String pushLog = request.getParameter("pushLog");
						if (StringUtils.isNotBlank(pushLog)) {
							JSONArray jsonArray = JSONArray.fromObject(pushLog);
							int arraySize = jsonArray.size();
							if (arraySize > 0) {
								JSONObject object;
								for (int i = 0; i < arraySize; i++) {
									object = JSONObject.fromObject(jsonArray.getJSONObject(i));
									int id = object.getInt("id");
									logBo.setUserDriverId(id);
									logBo.setOperationType(82);
									logBo.setOperationName("pushLogClicked");
									logBo.setRemark("查看通知");
									logRecorder.addReqLogToQueue(logBo);
								}
							}
						}
						ignore = true;
					} else {
			            boolean flag = false;
			            for (LogEnum logEnum : LogEnum.values()) {
			                if (logEnum.operationType() == type) {
			                    flag = true;

			                    logBo.setOperationType(type);
			                    logBo.setOperationName(logEnum.operationName());
			                    logBo.setRemark(logEnum.remark());

			                    break;
			                }

			            }

			            if (!flag) {
			                logger.warn("日志枚举对象中缺少对象。");
			            }
			        }
			        if (logger.isDebugEnabled()) {
			            logger.debug("向日志记录线程中添加日志对象....");
			        }
					if (!ignore) {
						logRecorder.addReqLogToQueue(logBo);
					}
			    }
			}
		} catch (Exception e) {
			logger.info("日志拦截中出现错误：" + e.getMessage());
			logger.error(e.getMessage());
		}

        super.postHandle(request, response, handler, modelAndView);
    }
}
