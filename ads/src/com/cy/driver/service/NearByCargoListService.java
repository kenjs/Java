package com.cy.driver.service;

import com.cy.driver.domain.OrderCargoInfoDomain;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Haoyong on 2015/1/14.
 */
public interface NearByCargoListService {

    /**
     * 找货
     * @param domain
     * @return
     * @throws SQLException
     */
    public List<OrderCargoInfoDomain> searchCargo(OrderCargoInfoDomain domain) throws SQLException;
}
