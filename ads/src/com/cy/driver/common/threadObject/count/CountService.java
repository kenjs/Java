package com.cy.driver.common.threadObject.count;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by haoy on 2014/10/8.
 */
public interface CountService {
    public int updateCount(List<Count> list) throws SQLException;
}
