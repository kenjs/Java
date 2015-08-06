package com.cy.swp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Richie.Lee on 2014/9/15.
 */
public interface WebAction {
    void setRequest(HttpServletRequest request);

    void setResponse(HttpServletResponse response);

    void setSession(HttpSession session);
}
