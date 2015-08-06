package com.cy.driver.common.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by haoy on 2014/9/19.
 */
public interface WebAction {
    void setRequest(HttpServletRequest request);
    void setResponse(HttpServletResponse response);
}
