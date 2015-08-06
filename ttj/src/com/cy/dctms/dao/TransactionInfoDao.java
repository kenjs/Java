package com.cy.dctms.dao;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by haoy on 2014/9/11.
 */
@Repository("transactionInfoDao")
public interface TransactionInfoDao {

    public List queryExportTransactionInfo(Map<String, Object> map) throws SQLException;
}
