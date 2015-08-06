package com.cy.swp.action;


import com.cy.swp.bo.MarketingUserInfo;
import com.cy.swp.common.constants.Constants;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 所有基类action
 *
 * @author hayden
 */
@Controller
public abstract class BaseAction implements WebAction {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    /**
     * 获取当前登录用户的session信息
     */
    public MarketingUserInfo getSessionUser() {
        if (request.getSession().getAttribute(Constants.SESSION_LOGIN_USER) != null) {
            return (MarketingUserInfo) request.getSession().getAttribute(
                    Constants.SESSION_LOGIN_USER);
        } else {
            return null;
        }
    }

    /**
     * 设置当前登录用户的session信息,GBK编码
     */
    public void putSessionUser(MarketingUserInfo marketingUserInfo) {
        request.getSession().setAttribute(Constants.SESSION_LOGIN_USER, marketingUserInfo);
    }
}
