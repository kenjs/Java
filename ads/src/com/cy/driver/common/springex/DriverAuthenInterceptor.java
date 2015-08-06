package com.cy.driver.common.springex;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.util.ValidateUtil;
import com.cy.driver.domain.DriverUserInfoDomain;
import com.cy.driver.service.AuthenticationService;
import com.cy.driver.service.OperationLogService;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by haoy on 2015/1/29.
 */
@Component("DriverAuthenInterceptor")
public class DriverAuthenInterceptor extends HandlerInterceptorAdapter {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AuthenticationService authenticationService;
    @Resource
    private OperationLogService operationLogService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        JsonGenerator jsonGenerator = new ObjectMapper().getJsonFactory().createJsonGenerator(response.getOutputStream(), JsonEncoding.UTF8);

        response.setContentType("application/json;charset=UTF-8");

        String driverId = request.getParameter("driverId");

        String requestUri = request.getRequestURI();

        String uri = requestUri.substring(requestUri.lastIndexOf("/") + 1);

        if ("checkNewVersion".equals(uri)) {
            return true;
        }

        if (uri.equals("updateReference")) {
            if (StringUtils.isBlank(driverId)) {
                JSonResponse jSonResponse = JSonResponse.makeHasContentJSonRespone("0", "司机ID不能为空");
                jsonGenerator.writeObject(jSonResponse);
                return false;
            }

            int id = Integer.parseInt(driverId);

            authenticationService.removeRefer(id);

            return true;
        }

        if (StringUtils.isBlank(driverId)) {
            return true;
        }

        if (!ValidateUtil.isNumberic(driverId)) {
            JSonResponse jSonResponse = JSonResponse.makeHasContentJSonRespone("-9", "该用户不存在或已被冻结");
            jsonGenerator.writeObject(jSonResponse);
            return false;
        }

        int id = Integer.parseInt(driverId);

        boolean isOk = authenticationService.isOk(id);

        if (!isOk) {
            DriverUserInfoDomain driverUserInfoDomain = operationLogService.checkUser(driverId);

            if (driverUserInfoDomain == null) {
                JSonResponse jSonResponse = JSonResponse.makeHasContentJSonRespone("-9", "该用户不存在或已被冻结");
                jsonGenerator.writeObject(jSonResponse);
                return false;
            }

            authenticationService.addRefer(driverUserInfoDomain.getId());
        }

        log.debug("拦截结束，已登录用户：{}", authenticationService.getRefer().size() - 1);

        return super.preHandle(request, response, handler);
    }
}
