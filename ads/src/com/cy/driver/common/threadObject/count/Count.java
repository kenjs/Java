package com.cy.driver.common.threadObject.count;

import java.io.Serializable;

/**
 * Created by haoy on 2014/10/8.
 */
public class Count implements Serializable{

    private String tableName;
    private String column;
    private int operaType;
    private int driverId;

    public Count() {
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public int getOperaType() {
        return operaType;
    }

    public void setOperaType(int operaType) {
        this.operaType = operaType;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }
}
