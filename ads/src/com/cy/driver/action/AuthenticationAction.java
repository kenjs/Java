package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.action.WebBaseAction;
import com.cy.driver.domain.DriverUserInfoDomain;
import com.cy.driver.service.OperationLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.lang.ref.SoftReference;
import java.util.List;

/**
 * Created by haoy on 2015/1/21.
 */
@Scope("prototype")
@Controller("authenticationAction")
public class AuthenticationAction extends WebBaseAction {
    protected static boolean isOk;

    protected static JSonResponse jSonResponse;

    protected static SoftReference<List<DriverUserInfoDomain>> softReference;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private OperationLogService operationLogService;

    protected void authentication(String driverId) throws Exception {
        isOk = true;
    }
}
