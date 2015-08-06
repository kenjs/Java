package com.cy.driver.dao;

import com.cy.driver.domain.ImgDeployInfoDomain;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by haoy on 2014/12/5.
 */
@Repository("viwePagerDao")
public interface ViwePagerDao {
    /**
     * 查询图片
     * @return
     * @throws SQLException
     */
    public List<ImgDeployInfoDomain> queryViewPagers() throws SQLException;
}
