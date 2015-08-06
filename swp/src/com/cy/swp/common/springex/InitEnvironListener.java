package com.cy.swp.common.springex;

import com.cy.swp.common.constants.SysEnviron;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Richie.Lee on 2014/9/9.
 */
public class InitEnvironListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        SysEnviron.WEB_ROOT_REALPATH = servletContextEvent.getServletContext().getRealPath("/");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
